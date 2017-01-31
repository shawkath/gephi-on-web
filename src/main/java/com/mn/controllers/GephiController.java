/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mn.controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.plugin.edge.EdgeWeightBuilder.EdgeWeightFilter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;
import org.gephi.layout.spi.Layout;
import org.gephi.partition.api.Partition;
import org.gephi.partition.api.PartitionController;
import org.gephi.partition.plugin.NodeColorTransformer;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.Modularity;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.EgoBuilder.EgoFilter;
import org.gephi.filters.plugin.partition.PartitionBuilder.NodePartitionFilter;
import org.gephi.graph.api.GraphView;

import org.openide.util.Lookup;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GephiController {

    @Autowired
    ServletContext servletContext;


    @RequestMapping(value = "/applyfilter", method = RequestMethod.POST)
    public @ResponseBody
    String materialMapping(@QueryParam("filename") String filename,
            @QueryParam("layout") String layout,
            @QueryParam("statistic") String statistic, @QueryParam("filter") String filter, HttpServletRequest servletrequest) {

        ProjectController pc = Lookup.getDefault().lookup(
                ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        ImportController importController = Lookup.getDefault().lookup(
                ImportController.class);
        Container container = null;
        String filepath = servletContext.getRealPath("/resources/gephi/" + filename);
        try {
            ClassLoader classLoader = GephiController.class
                    .getClassLoader();

            System.out.println(filepath);
            File file = new File(filepath);
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDefault.DIRECTED);
            container.setAllowAutoNode(false); // don't create missing nodes
        } catch (Exception ex) {
            ex.printStackTrace();
            // return;
        }

        // Append imported data to graph api
        importController.process(container, new DefaultProcessor(), workspace);

        GraphModel graphModel = Lookup.getDefault()
                .lookup(GraphController.class).getModel();
        DirectedGraph directedGraph = graphModel.getDirectedGraph();
        // Now let's manipulate the graph api, which stores / serves graphs
        System.out.println("Nodes: " + directedGraph.getNodeCount()
                + "\nEdges: " + directedGraph.getEdgeCount());

        // Run OpenOrd.
        // OpenOrdLayout layout = new OpenOrdLayout(null);
        AttributeModel attributemodel = Lookup.getDefault()
                .lookup(AttributeController.class).getModel();

        // Get modularity for coloring
        if (!layout.equals("")) {
            applyLayout(layout, graphModel);
        }
        if (!statistic.equals("")) {
            applyStatistics(statistic, graphModel, attributemodel,
                    directedGraph);
        }
        if (!filter.equals("")) {
            applyFilter(filter, graphModel,attributemodel);
        }
        // Ranking
        /*RankingController rankingController = Lookup.getDefault().lookup(
         RankingController.class);
         Ranking degreeRanking = rankingController.getModel().getRanking(
         Ranking.NODE_ELEMENT, Ranking.INDEGREE_RANKING);
         AbstractSizeTransformer sizeTransformer = (AbstractSizeTransformer) rankingController
         .getModel().getTransformer(Ranking.NODE_ELEMENT,
         org.gephi.ranking.api.Transformer.RENDERABLE_SIZE);
         sizeTransformer.setMinSize(5.0f);
         sizeTransformer.setMaxSize(40.0f);
         rankingController.transform(degreeRanking, sizeTransformer);*/

        // Finally, the preview model
        PreviewController previewController = Lookup.getDefault().lookup(
                PreviewController.class);
        PreviewModel previewModel = previewController.getModel();
        previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS,
                Boolean.TRUE);
        // previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR,
        // new DependantOriginalColor(Color.BLACK));
        previewModel.getProperties().putValue(
                PreviewProperty.NODE_LABEL_FONT,
                previewModel.getProperties()
                .getFontValue(PreviewProperty.NODE_LABEL_FONT)
                .deriveFont(8));
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED,
                Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
        // previewModel.getProperties().putValue(PreviewProperty.EDGE_RADIUS,
        // 10f);
        // previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR,
        // Color.TRANSLUCENT);

        previewController.refreshPreview();

        System.out.println("starting export");

        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        /*PNGExporter pngExporter = (PNGExporter) ec.getExporter("png");
         pngExporter.setWorkspace(workspace);
         try {
         ec.exportFile(new File(servletContext.getRealPath("/resources/gephi/Yifanlayoutgraph.png")), pngExporter);
         } catch (IOException ex) {
         ex.printStackTrace();
         
         }*/

        GraphExporter exporter = (GraphExporter) ec.getExporter("gexf");
        exporter.setExportVisible(true); // Only exports the visible (filtered)
        // graph
        exporter.setWorkspace(workspace);
        try {
            ec.exportFile(new File(filepath), exporter);
        } catch (IOException ex) {
            ex.printStackTrace();
            // return;
        }

        System.out.println("Done.");
        return "success";
    }

    public Layout applyLayout(String layoutStr, GraphModel graphModel) {
        /*
         * clockwiserotate contraction counterclockwiserotate expansion
         * fruchtermanreigngold yifanhu
         */

        if (layoutStr.equals("yifanhu")) {
            YifanHuLayout layout = new YifanHuLayout(null,
                    new StepDisplacement(0.95f));
            layout.setGraphModel(graphModel);
            layout.resetPropertiesValues();
            layout.initAlgo();
            layout.goAlgo();
            while (layout.canAlgo()) // This is only possible because OpenOrd
            // has a finite number of iterations.
            {
                layout.goAlgo();
            }
        }
        if (layoutStr.equals("fruchtermanreigngold")) {
            FruchtermanReingold layout = new FruchtermanReingold(null);
            layout.setGraphModel(graphModel);
            layout.setArea(new Float(10000.0));
            layout.setGravity(10.0);
            layout.setSpeed(1.0);
            layout.initAlgo();
            layout.goAlgo();
            int i = 0;
            while (layout.canAlgo()) {
                layout.goAlgo();
                if (++i > 1000) {
                    break;
                }
            }
        }
        return null;
    }

    public void applyStatistics(String statistic, GraphModel graphModel,
            AttributeModel attributemodel, DirectedGraph directedGraph) {
        /*
         * averagedegree avgwighteddegree hits modularity pagerank
         */
        if (statistic.equals("modularity")) {
            Modularity modularity = new Modularity();
            modularity.setUseWeight(true);
            modularity.setRandom(true);
            modularity.setResolution(1.0);
            modularity.execute(graphModel, attributemodel);
            // Partition with modularity
            AttributeColumn modcol = attributemodel.getNodeTable().getColumn(
                    Modularity.MODULARITY_CLASS);
            PartitionController partitionController = Lookup.getDefault()
                    .lookup(PartitionController.class);
            Partition p = partitionController.buildPartition(modcol,
                    directedGraph);
            NodeColorTransformer nodeColorTransformer = new NodeColorTransformer();
            nodeColorTransformer.randomizeColors(p);
            partitionController.transform(p, nodeColorTransformer);
        }

    }

    private void applyFilter(String filterStr, GraphModel graphModel,AttributeModel attributemodel) {
        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        if (filterStr.equals("edgeweight")) {

            
            EdgeWeightFilter filter = new EdgeWeightFilter();            
            filter.init(graphModel.getGraph());
            Double lbrange=Double.valueOf(29000);
            Double ubrange=Double.MAX_VALUE;
            System.out.println(lbrange.getClass() + "," + ubrange.getClass());
            filter.setRange(new Range(lbrange,ubrange,lbrange,ubrange));   
            try{filter.wait(500);}catch(Exception ex){}
            filter.finish();
            Query query = filterController.createQuery(filter);
            GraphView view = filterController.filter(query);
            graphModel.setVisibleView(view);
              DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
        }
        else if (filterStr.equals("nodepartition")){
            PartitionController partitionController = Lookup.getDefault().lookup(PartitionController.class);
        Partition p = partitionController.buildPartition(attributemodel.getNodeTable().getColumn("Id"), graphModel.getGraph());
        NodePartitionFilter partitionFilter = new NodePartitionFilter(p);
        partitionFilter.unselectAll();
        partitionFilter.addPart(p.getPartFromValue("C01"));
        Query query2 = filterController.createQuery(partitionFilter);
        GraphView view2 = filterController.filter(query2);
        graphModel.setVisibleView(view2);    //Set the filter resu
            DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
        }
        else if (filterStr.equals("egonetwork")){
             EgoFilter egoFilter = new EgoFilter();
        egoFilter.setPattern("C01"); //Regex accepted
        egoFilter.setDepth(1);
        Query queryEgo = filterController.createQuery(egoFilter);
        GraphView viewEgo = filterController.filter(queryEgo);
        graphModel.setVisibleView(viewEgo);  
          DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
        }
    }
}

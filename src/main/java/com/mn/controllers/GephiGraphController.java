//package com.mn.controllers;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//
//import org.gephi.data.attributes.api.AttributeColumn;
//import org.gephi.data.attributes.api.AttributeController;
//import org.gephi.data.attributes.api.AttributeModel;
//import org.gephi.graph.api.DirectedGraph;
//import org.gephi.graph.api.GraphController;
//import org.gephi.graph.api.GraphModel;
//import org.gephi.io.exporter.api.ExportController;
//import org.gephi.io.exporter.spi.GraphExporter;
//import org.gephi.io.importer.api.Container;
//import org.gephi.io.importer.api.EdgeDefault;
//import org.gephi.io.importer.api.ImportController;
//import org.gephi.io.processor.plugin.DefaultProcessor;
//import org.gephi.layout.plugin.force.StepDisplacement;
//import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
//import org.gephi.layout.spi.Layout;
//import org.gephi.partition.api.Partition;
//import org.gephi.partition.api.PartitionController;
//import org.gephi.partition.plugin.NodeColorTransformer;
//import org.gephi.preview.api.PreviewController;
//import org.gephi.preview.api.PreviewModel;
//import org.gephi.preview.api.PreviewProperty;
//import org.gephi.project.api.ProjectController;
//import org.gephi.project.api.Workspace;
//import org.gephi.ranking.api.Ranking;
//import org.gephi.ranking.api.RankingController;
//import org.gephi.ranking.plugin.transformer.AbstractSizeTransformer;
//import org.gephi.ranking.plugin.transformer.LabelColorTransformerBuilder.LabelColorTransformer;
//import org.gephi.statistics.plugin.Modularity;
//import org.openide.util.Lookup;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class GephiGraphController {
////	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	//@Path("/generategraph")
//	@RequestMapping(value = "/generategraph", method = RequestMethod.POST)
//	public String materialMapping(@QueryParam("filename") String filename,
//			@QueryParam("layout") String layout,
//			@QueryParam("statistic") String statistic) {
//		ProjectController pc = Lookup.getDefault().lookup( 
//				ProjectController.class);
//		pc.newProject();
//		Workspace workspace = pc.getCurrentWorkspace(); 
// 
//		ImportController importController = Lookup.getDefault().lookup(
//				ImportController.class);
//		Container container = null;
//		try {
//			ClassLoader classLoader = GephiGraphController.class
//					.getClassLoader();
//			File file = new File(classLoader.getResource(filename).getFile());
//			container = importController.importFile(file);
//			container.getLoader().setEdgeDefault(EdgeDefault.DIRECTED);
//			container.setAllowAutoNode(false); // don't create missing nodes
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			// return;
//		}
//
//		// Append imported data to graph api
//		importController.process(container, new DefaultProcessor(), workspace);
//
//		GraphModel graphModel = Lookup.getDefault()
//				.lookup(GraphController.class).getModel();
//		DirectedGraph directedGraph = graphModel.getDirectedGraph();
//		// Now let's manipulate the graph api, which stores / serves graphs
//		System.out.println("Nodes: " + directedGraph.getNodeCount()
//				+ "\nEdges: " + directedGraph.getEdgeCount());
//
//		// Run OpenOrd.
//		// OpenOrdLayout layout = new OpenOrdLayout(null);
//
//		AttributeModel attributemodel = Lookup.getDefault()
//				.lookup(AttributeController.class).getModel();
//
//		// Get modularity for coloring
//		if (!layout.equals(""))
//			applyLayout(layout, graphModel);
//		if (!statistic.equals(""))
//			applyStatistics(statistic, graphModel, attributemodel,
//					directedGraph);
//
//		// Ranking
//		RankingController rankingController = Lookup.getDefault().lookup(
//				RankingController.class);
//		Ranking degreeRanking = rankingController.getModel().getRanking(
//				Ranking.NODE_ELEMENT, Ranking.INDEGREE_RANKING);
//		AbstractSizeTransformer sizeTransformer = (AbstractSizeTransformer) rankingController
//				.getModel().getTransformer(Ranking.NODE_ELEMENT,
//						org.gephi.ranking.api.Transformer.RENDERABLE_SIZE);
//		sizeTransformer.setMinSize(5.0f);
//		sizeTransformer.setMaxSize(40.0f);
//		rankingController.transform(degreeRanking, sizeTransformer);
//
//		// Finally, the preview model
//		PreviewController previewController = Lookup.getDefault().lookup(
//				PreviewController.class);
//		PreviewModel previewModel = previewController.getModel();
//		previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS,
//				Boolean.TRUE);
//		// previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR,
//		// new DependantOriginalColor(Color.BLACK));
//		previewModel.getProperties().putValue(
//				PreviewProperty.NODE_LABEL_FONT,
//				previewModel.getProperties()
//						.getFontValue(PreviewProperty.NODE_LABEL_FONT)
//						.deriveFont(8));
//		previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED,
//				Boolean.FALSE);
//		previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
//		// previewModel.getProperties().putValue(PreviewProperty.EDGE_RADIUS,
//		// 10f);
//		// previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR,
//		// Color.TRANSLUCENT);
//
//		previewController.refreshPreview();
//
//		System.out.println("starting export");
//		
//		  ExportController ec =Lookup.getDefault().lookup(ExportController.class); 
//		  /*PNGExporter
//		 * pngExporter = (PNGExporter) ec.getExporter("png");
//		 * pngExporter.setWorkspace(workspace); try { ec.exportFile(new
//		 * File("Yifanlayoutgraph.png"), pngExporter); } catch (IOException ex)
//		 * { ex.printStackTrace(); //return; }
//		 */
//		GraphExporter exporter = (GraphExporter) ec.getExporter("gexf");
//		exporter.setExportVisible(true); // Only exports the visible (filtered)
//											// graph
//		exporter.setWorkspace(workspace);
//		try {
//			ec.exportFile(new File(filename), exporter);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			// return;
//		}
//
//		
//		System.out.println("Done.");
//		return "success";
//	}
//
//	public Layout applyLayout(String layoutStr, GraphModel graphModel) {
//		/*
//		 * clockwiserotate contraction counterclockwiserotate expansion
//		 * fruchtermanreigngold yifanhu
//		 */
//
//		if (layoutStr.equals("yifanhu")) {
//			YifanHuLayout layout = new YifanHuLayout(null,
//					new StepDisplacement(0.95f));
//			layout.setGraphModel(graphModel);
//			layout.resetPropertiesValues();
//			layout.initAlgo();
//			layout.goAlgo();
//			while (layout.canAlgo()) // This is only possible because OpenOrd
//										// has a finite number of iterations.
//			{
//				layout.goAlgo();
//			}
//		}
//		return null;
//	}
//
//	public void applyStatistics(String statistic, GraphModel graphModel,
//			AttributeModel attributemodel, DirectedGraph directedGraph) {
//		/*
//		 * averagedegree avgwighteddegree hits modularity pagerank
//		 */
//		if (statistic.equals("modularity")) {
//			Modularity modularity = new Modularity();
//			modularity.setUseWeight(true);
//			modularity.setRandom(true);
//			modularity.setResolution(1.0);
//			modularity.execute(graphModel, attributemodel);
//			// Partition with modularity
//			AttributeColumn modcol = attributemodel.getNodeTable().getColumn(
//					Modularity.MODULARITY_CLASS);
////			AttributeColumn filCol = attributemodel.getEdgeTable().getColumn(LabelColorTransformer.LABEL_COLOR);
//			
//			PartitionController partitionController = Lookup.getDefault()
//					.lookup(PartitionController.class);
//			Partition p = partitionController.buildPartition(modcol,
//					directedGraph);
//			NodeColorTransformer nodeColorTransformer = new NodeColorTransformer();
//			nodeColorTransformer.randomizeColors(p);
//			partitionController.transform(p, nodeColorTransformer);
//		}
//	}
//	
//	void generateModularity(List<String> nodes, List<String> edges){
//		Modularity mod = new Modularity();
//		mod.getModularity();
////		mod.execute(nodes, edges);
//	}
//	
//	
//}
//

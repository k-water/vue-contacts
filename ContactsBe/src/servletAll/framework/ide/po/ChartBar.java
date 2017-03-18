package servletAll.framework.ide.po;

import java.util.ArrayList;
import java.util.List;

public class ChartBar {
	
	private List<String> labels = new ArrayList<String>();
	private List<Datasets> datasets;
	
	
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<Datasets> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<Datasets> datasets) {
		this.datasets = datasets;
	}
	
	
}

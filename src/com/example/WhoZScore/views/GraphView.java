package com.example.WhoZScore.views;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.WhoZScore.R;
import com.example.WhoZScore.WhoZScore;
import com.example.WhoZScore.core.Calculator;

import com.example.WhoZScore.model.GraphModel;
import com.example.WhoZScore.model.Patient;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyasl
 * Date: 4/5/15
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphView extends Fragment {

    private View mChart;
    private Calculator calculator = new Calculator();
    private LinearLayout chartLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.graph_view, container, false);
        Patient patient = ((WhoZScore) getActivity()).getPatient();
        chartLayout = (LinearLayout) view.findViewById(R.id.chart);
        openChart(calculator.getGraphModel(patient,null));
        return view;
    }


    public void openChart(GraphModel graphModel){
        int[] x = toIntArray(graphModel.getxAxis());
        int[] minusThreeScore = toIntArray(graphModel.getMinusThreeScore());
        int[] minusTwoScore = toIntArray(graphModel.getMinusTwoScore());
        int[] minusOneScore = toIntArray(graphModel.getMinusOneScore());
        int[] zeroScore = toIntArray(graphModel.getZeroScore());
        int[] oneScore = toIntArray(graphModel.getOneScore());
        int[] twoScore = toIntArray(graphModel.getTwoScore());
        int[] threeScore = toIntArray(graphModel.getThreeScore());


        XYSeries minusThreeXYSeries = new XYSeries("-3");
        XYSeries minusTwoXYSeries = new XYSeries("-2");
        XYSeries minusOneXYSeries = new XYSeries("-1");
        XYSeries zeroXYSeries = new XYSeries("0");
        XYSeries oneXYSeries = new XYSeries("1");
        XYSeries twoXYSeries = new XYSeries("2");
        XYSeries threeXYSeries = new XYSeries("3");
        for(int i=0;i<x.length;i++){
            minusThreeXYSeries.add(i, minusThreeScore[i]);
            minusTwoXYSeries.add(i, minusTwoScore[i]);
            minusOneXYSeries.add(i, minusOneScore[i]);
            zeroXYSeries.add(i, zeroScore[i]);
            oneXYSeries.add(i, oneScore[i]);
            twoXYSeries.add(i, twoScore[i]);
            threeXYSeries.add(i, threeScore[i]);

        }

// Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(minusThreeXYSeries);
        dataset.addSeries(minusTwoXYSeries);
        dataset.addSeries(minusOneXYSeries);
        dataset.addSeries(zeroXYSeries);
        dataset.addSeries(oneXYSeries);
        dataset.addSeries(twoXYSeries);
        dataset.addSeries(threeXYSeries);

// Creating XYSeriesRenderer to customize renderers

        XYSeriesRenderer minusThreeRenderer = createXYSeriesRenderer(-3);
        XYSeriesRenderer minusTwoRenderer = createXYSeriesRenderer(-2);
        XYSeriesRenderer minusOneRenderer = createXYSeriesRenderer(-1);
        XYSeriesRenderer zeroRenderer = createXYSeriesRenderer(0);
        XYSeriesRenderer oneRenderer = createXYSeriesRenderer(1);
        XYSeriesRenderer twoRenderer = createXYSeriesRenderer(2);
        XYSeriesRenderer threeRenderer = createXYSeriesRenderer(3);




// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle(graphModel.getzScoreGraphTypes().getGraphNames());
        multiRenderer.setXTitle("Age in "+ graphModel.getAge().getName());
        multiRenderer.setYTitle(graphModel.getzScoreGraphTypes().getyAxis());

/***
 * Customizing graphs
 */
//setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
//setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
//setting text size of the graph lable
        multiRenderer.setLabelsTextSize(24);
//setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
//setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(false, false);
//setting click false on graph
        multiRenderer.setClickEnabled(false);
//setting zoom to false on both axis
        multiRenderer.setZoomEnabled(false, false);
//setting lines to display on y axis
        multiRenderer.setShowGridY(true);
//setting lines to display on x axis
        multiRenderer.setShowGridX(true);
//setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
//setting displaying line on grid
        multiRenderer.setShowGrid(true);
//setting zoom to false
        multiRenderer.setZoomEnabled(false);
//setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
//setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
//setting to in scroll to false
        multiRenderer.setInScroll(false);
//setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
//setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
//setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
//setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
//setting no of values to display in y axis
        multiRenderer.setYLabels(10);
// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
// if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(4000);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMax(11);
//setting bar size or space between two bars
//multiRenderer.setBarSpacing(0.5);
//Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
//Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
//setting x axis point size
        multiRenderer.setPointSize(4f);
//setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});



// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
// should be same
        multiRenderer.addSeriesRenderer(minusThreeRenderer);
        multiRenderer.addSeriesRenderer(minusTwoRenderer);
        multiRenderer.addSeriesRenderer(minusOneRenderer);
        multiRenderer.addSeriesRenderer(zeroRenderer);
        multiRenderer.addSeriesRenderer(oneRenderer);
        multiRenderer.addSeriesRenderer(twoRenderer);
        multiRenderer.addSeriesRenderer(threeRenderer);


//remove any views before u paint the chart
        chartLayout.removeAllViews();
//drawing bar chart
        mChart = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);
//adding the view to the linearlayout
        chartLayout.addView(mChart);

    }

    private XYSeriesRenderer createXYSeriesRenderer(int i) {
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        switch (i){
            case -3 : renderer.setColor(Color.BLACK);
                      break;
            case -2 : renderer.setColor(Color.RED);
                      break;
            case -1 : renderer.setColor(17170457);
                      break;
            case 0 : renderer.setColor(Color.GREEN);
                     break;
            case 3 : renderer.setColor(Color.BLACK);
                     break;
            case 2 : renderer.setColor(Color.RED);
                     break;
            case 1 : renderer.setColor(17170457);
                     break;
        }
        renderer.setFillPoints(true);
        renderer.setLineWidth(2f);
        renderer.setDisplayChartValues(true);
//setting chart value distance
        renderer.setDisplayChartValuesDistance(10);
//setting line graph point style to circle
        renderer.setPointStyle(PointStyle.CIRCLE);
//setting stroke of the line chart to solid
        renderer.setStroke(BasicStroke.SOLID);
        return renderer;
    }

    int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }
}

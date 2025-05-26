/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PANELS;
import DAO.ThongKeDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author THIS PC
 */
public class ThongKe extends javax.swing.JPanel {
    private ThongKeDAO thongKeDAO;

    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        thongKeDAO = new ThongKeDAO();
        initComponents();
        createStatsPanel();
    }
    
    private void createStatsPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Panel thống kê sách theo thể loại
        JPanel genrePanel = createGenreStatsPanel();
        tabbedPane.addTab("Sách theo thể loại", genrePanel);
        
        // Panel thống kê sách đang mượn
        JPanel borrowingPanel = createBorrowingStatsPanel();
        tabbedPane.addTab("Sách đang mượn", borrowingPanel);
        
        // Panel top 5 sách được mượn nhiều
        JPanel topBooksPanel = createTopBooksPanel();
        tabbedPane.addTab("Top sách mượn", topBooksPanel);
        
        // Panel thống kê trạng thái phiếu
        JPanel ticketStatusPanel = createTicketStatusPanel();
        tabbedPane.addTab("Trạng thái phiếu", ticketStatusPanel);
        
        // Thêm JTabbedPane vào jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createGenreStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultPieDataset dataset = thongKeDAO.getGenreStats(); 
        JFreeChart chart = ChartFactory.createPieChart(
            "Thống kê số lượng sách theo thể loại",
            dataset,
            true, 
            true, 
            false 
        );
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setBackgroundPaint(Color.WHITE); 
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 300));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createBorrowingStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultCategoryDataset dataset = thongKeDAO.getBorrowingStats();
        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê sách đang mượn",
            "Danh mục",
            "Số lượng",
            dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);   
        renderer.setSeriesPaint(1, Color.GREEN);  
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 300));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createTopBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultCategoryDataset dataset = thongKeDAO.getTopBooks();
        JFreeChart chart = ChartFactory.createBarChart(
            "Top 5 sách được mượn nhiều nhất",
            "Tên sách",
            "Số lần mượn",
            dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE}; 
        for (int i = 0; i < Math.min(dataset.getColumnCount(), colors.length); i++) {
            renderer.setSeriesPaint(i, colors[i]);
        }
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 300));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createTicketStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultCategoryDataset dataset = thongKeDAO.getTicketStatus();
        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê trạng thái phiếu mượn",
            "Trạng thái",
            "Số lượng phiếu",
            dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}; 
        for (int i = 0; i < Math.min(dataset.getColumnCount(), colors.length); i++) {
            renderer.setSeriesPaint(i, colors[i]);
        }
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 300));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

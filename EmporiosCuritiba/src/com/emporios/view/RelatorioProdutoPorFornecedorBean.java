package com.emporios.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emporios.controller.ProdutoFornecedorController;
import com.emporios.dto.ProdutoFornecedorDTO;
import com.emporios.jasper.JasperFactoryProdutosPorFornecedor;
import com.emporios.model.ProdutoFornecedor;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioProdutoPorFornecedorBean
{
    public static void RelatorioProdutoPorFornecedor() throws JRException{

        JasperReport jasperReport = null;
		try {
			jasperReport = JasperFactoryProdutosPorFornecedor.getRelacaoProdutoPorFornecedor();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Produto", "EmporiosCuritiba");

        // Obtendo a lista de Usuarios
        ProdutoFornecedorDTO tDto = ProdutoFornecedorController.produtoPorFornecedor();
        if (tDto.isOk())
        {
            List<ProdutoFornecedor> tLista = tDto.getLista();
            // DataSource
            JRDataSource dataSource = new JRBeanCollectionDataSource(tLista);

            System.out.println();
            System.out.println("Preenchendo lista geral");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Make sure the output directory exists.
            File outDir = new File("./jasperoutput");
            outDir.mkdirs();

            // Export to PDF.
            System.out.println();
            System.out.println("Exportando lista geral");
            JasperExportManager.exportReportToPdfFile(jasperPrint,"./jasperoutput/produtoPorFornecedor.pdf");

            System.out.println("Done!");
        }

        // Obtendo a lista de usuarios
        tDto = ProdutoFornecedorController.produtoPorFornecedor();
        if (tDto.isOk())
        {
            List<ProdutoFornecedor> tLista = tDto.getLista();

            // DataSource
            JRDataSource dataSource = new JRBeanCollectionDataSource(tLista);

            System.out.println();
            System.out.println("Preenchendo lista por nome");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Make sure the output directory exists.
            File outDir = new File("./jasperoutput");
            outDir.mkdirs();

            // Export to PDF.
            System.out.println();
            System.out.println("Exportando lista por nome");
            JasperExportManager.exportReportToPdfFile(jasperPrint,"./jasperoutput/produtoPorFornecedor.pdf");

            System.out.println("Done!");
        }
    }
}

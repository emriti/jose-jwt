package apiservices.report.services;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportBaseService {

	private static String reportPath = "d:/reports";

	private static String imageReportPath = "";

	public static void setReportPath(String path) {
		//reportPath = path + "/Reports";
		reportPath = path;
	}

	public String getReportPath() {
		return reportPath;
	}

	// public static String getAppPath3() {
	// //URL location =
	// ReportBaseServiceImpl.class.getProtectionDomain().getCodeSource().getLocation();
	// URL location =
	// ReportBaseServiceImpl.class.getClassLoader().getResource("");
	// String path = location.getFile();
	//
	// String result = "";
	//
	// try {
	// String rootPath = new File(path).getParent().replace("\\", "/");
	//
	// result = URLDecoder.decode(rootPath, "UTF-8");
	//
	// File folderRoot = new File(result);
	// if (!folderRoot.exists()) {
	// folderRoot.mkdir();
	// }
	//
	// } catch (Exception e) {
	// result = "";
	// }
	//
	// return result.replace("/WEB-INF", "");
	// }
	//
	// public static String getAppPath(String aPath) {
	// String path = "";
	//
	// try {
	// org.jboss.vfs.VirtualFile vFile =
	// org.jboss.vfs.VFS.getChild(ReportBaseServiceImpl.class.getClassLoader().getResource("").toString());
	//
	// URI fileNameDecodedTmp = org.jboss.vfs.VFSUtils.getPhysicalURI(vFile);
	//
	// path = fileNameDecodedTmp.getPath();
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return path;
	// }

	public byte[] doExportXls(String reportName, String jasperName, List listData, Map<String, Object> parameters)
			throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsExporter xlsExporter = new JRXlsExporter();

			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
			xlsReportConfiguration.setOnePagePerSheet(false);
			// xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsReportConfiguration.setDetectCellType(true);
			xlsReportConfiguration.setWhitePageBackground(false);

			String[] sheetNames = new String[] { "Sheet1" };
			xlsReportConfiguration.setSheetNames(sheetNames);

			xlsExporter.setConfiguration(xlsReportConfiguration);
			xlsExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public byte[] doExportXlsx(String reportName, String jasperName, List listData, Map<String, Object> parameters)
			throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsxExporter xlsxExporter = new JRXlsxExporter();

			xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
			xlsxReportConfiguration.setOnePagePerSheet(false);
			// xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsxReportConfiguration.setDetectCellType(true);
			xlsxReportConfiguration.setWhitePageBackground(false);

			String[] sheetNames = new String[] { "Sheet1" };
			xlsxReportConfiguration.setSheetNames(sheetNames);

			xlsxExporter.setConfiguration(xlsxReportConfiguration);
			xlsxExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public byte[] doExportPdf(String reportName, String jasperName, List listData, Map<String, Object> parameters)
			throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRPdfExporter pdfExporter = new JRPdfExporter();

			pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setCreatingBatchModeBookmarks(true);
			pdfExporter.setConfiguration(configuration);
			pdfExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public byte[] doExportHtml(String jasperName, List listData, Map<String, Object> parameters) throws Exception {
		File file = File.createTempFile("tmpReportFile", ".html");
		byte[] byteReport = null;
		try {
			// cek list size
//			if (listData == null || listData.size() == 0) {
//				throw new Exception("No Data to Export");
//			}

			
			
			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperRunManager.runReportToHtmlFile(reportJasper, file.getPath(), parameters,
					new JRBeanCollectionDataSource(listData));

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public byte[] doExportXlsMultiSheet(String reportName, String jasperName, List listData,
			Map<String, Object> parameters, String[] sheetNames) throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
			// xlsReportConfiguration.setOnePagePerSheet(true);
			// xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsReportConfiguration.setDetectCellType(true);
			xlsReportConfiguration.setWhitePageBackground(false);
			xlsReportConfiguration.setSheetNames(sheetNames);
			xlsReportConfiguration.setMaxRowsPerSheet(10000000);

			xlsExporter.setConfiguration(xlsReportConfiguration);
			xlsExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;

	}

	public byte[] doExportXlsxMultiSheet(String reportName, String jasperName, List listData,
			Map<String, Object> parameters, String[] sheetNames) throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsxExporter xlsxExporter = new JRXlsxExporter();
			xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
			// xlsxReportConfiguration.setOnePagePerSheet(false);
			xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsxReportConfiguration.setDetectCellType(true);
			xlsxReportConfiguration.setWhitePageBackground(false);
			xlsxReportConfiguration.setSheetNames(sheetNames);

			xlsxExporter.setConfiguration(xlsxReportConfiguration);
			xlsxExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;

	}

	public byte[] doExportXlsEmptyRowRemoved(String reportName, String jasperName, List listData,
			Map<String, Object> parameters) throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsExporter xlsExporter = new JRXlsExporter();

			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
			xlsReportConfiguration.setOnePagePerSheet(false);
			xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsReportConfiguration.setDetectCellType(true);
			xlsReportConfiguration.setWhitePageBackground(false);

			String[] sheetNames = new String[] { "Sheet1" };
			xlsReportConfiguration.setSheetNames(sheetNames);

			xlsExporter.setConfiguration(xlsReportConfiguration);
			xlsExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public byte[] doExportXlsxEmptyRowRemoved(String reportName, String jasperName, List listData,
			Map<String, Object> parameters) throws Exception {
		File file = File.createTempFile("tmpReportFile", reportName.substring(reportName.lastIndexOf(".")));
		byte[] byteReport = null;
		try {
			// cek list size
			if (listData == null || listData.size() == 0) {
				throw new Exception("No Data to Export");
			}

			// path jasper report
			String reportJasper = reportPath + jasperName;

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJasper, parameters,
					new JRBeanCollectionDataSource(listData));

			JRXlsxExporter xlsxExporter = new JRXlsxExporter();

			xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

			SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
			xlsxReportConfiguration.setOnePagePerSheet(false);
			xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
			xlsxReportConfiguration.setDetectCellType(true);
			xlsxReportConfiguration.setWhitePageBackground(false);

			String[] sheetNames = new String[] { "Sheet1" };
			xlsxReportConfiguration.setSheetNames(sheetNames);

			xlsxExporter.setConfiguration(xlsxReportConfiguration);
			xlsxExporter.exportReport();

			// convert file temp to byte
			byteReport = Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}

		return byteReport;
	}

	public String getImageReportPath() {
		return imageReportPath;
	}

	public static void setImageReportPath(String path) {
		imageReportPath = path + "/WEB-INF/classes/static/images";
	}

}

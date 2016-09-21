package com.ecomindo.common.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileDownloadUtil {
	public static void save(byte[] content, String filePath) throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void save(byte[] content, File file) throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw e;
		}
	}

	public static String saveAsDialog() {
		String pathFilename = null;
		try {
			// parent component of the dialog
			JFrame parentFrame = new JFrame();

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");

			// set file filter
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (.pdf)", "pdf"));
			fileChooser.addChoosableFileFilter(
					new FileNameExtensionFilter("MS Office Documents (.doc, .docx, .xls, .xlsx, .ppt, .pptx)", "doc",
							"docx", "xls", "xlsx", "ppt", "pptx"));
			fileChooser.addChoosableFileFilter(
					new FileNameExtensionFilter("Image Files (.jpg, .png, .gif, .bmp)", "jpg", "png", "gif", "bmp"));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt"));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archive Files (.zip, .rar)", "zip", "rar"));
			fileChooser.setAcceptAllFileFilterUsed(true);

			// show save dialog
			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				pathFilename = fileToSave.getAbsolutePath();
			} else if (userSelection == JFileChooser.CANCEL_OPTION) {
				pathFilename = "";
			}
		} catch (Exception e) {
			pathFilename = "";
		}
		return pathFilename;
	}

	public static String saveAsDialog(String filename) {
		String pathFilename = null;
		try {
			// parent component of the dialog
			JFrame parentFrame = new JFrame();
			parentFrame.setVisible(true);
			parentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");

			// set default filename
			fileChooser.setSelectedFile(new File(filename));

			// set file filter
			if (filename.endsWith(".pdf")) {
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (.pdf)", "pdf"));
			} else if (filename.endsWith(".doc") || filename.endsWith(".docx") || filename.endsWith(".xls")
					|| filename.endsWith(".xlsx") || filename.endsWith(".ppt") || filename.endsWith(".pptx")) {
				fileChooser.addChoosableFileFilter(
						new FileNameExtensionFilter("MS Office Documents (.doc, .docx, .xls, .xlsx, .ppt, .pptx)",
								"doc", "docx", "xls", "xlsx", "ppt", "pptx"));
			} else if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".gif")
					|| filename.endsWith(".bmp")) {
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files (.jpg, .png, .gif, .bmp)",
						"jpg", "png", "gif", "bmp"));
			} else if (filename.endsWith(".txt")) {
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt"));
			} else if (filename.endsWith(".zip") || filename.endsWith(".rar")) {
				fileChooser.addChoosableFileFilter(
						new FileNameExtensionFilter("Archive Files (.zip, .rar)", "zip", "rar"));
			} else {
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (.pdf)", "pdf"));
				fileChooser.addChoosableFileFilter(
						new FileNameExtensionFilter("MS Office Documents (.doc, .docx, .xls, .xlsx, .ppt, .pptx)",
								"doc", "docx", "xls", "xlsx", "ppt", "pptx"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files (.jpg, .png, .gif, .bmp)",
						"jpg", "png", "gif", "bmp"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt"));
				fileChooser.addChoosableFileFilter(
						new FileNameExtensionFilter("Archive Files (.zip, .rar)", "zip", "rar"));
			}

			fileChooser.setAcceptAllFileFilterUsed(true);

			// show save dialog
			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				pathFilename = fileToSave.getAbsolutePath();
			} else if (userSelection == JFileChooser.CANCEL_OPTION) {
				pathFilename = "";
			}
		} catch (Exception e) {
			pathFilename = "";
		}

		return pathFilename;
	}
}

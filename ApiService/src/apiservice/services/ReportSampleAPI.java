package apiservice.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import apiservices.report.services.ReportBaseService;

@Path("/reportsampleapi")
public class ReportSampleAPI {

	@Path("/show")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response show() throws Exception {
//		List<FileResponse> result = new ArrayList<FileResponse>();
//		FileResponse response = new FileResponse();
		try {
			
			// number from date
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String date_to_string = dateFormat.format(new Date());
			String randomString = "ABC";

			String reportName = "Report_1_" + randomString + date_to_string + ".html";
			String jasperName = "/report1.jasper";

			ReportBaseService rptSvc = new ReportBaseService();
			byte[] fileReport = rptSvc.doExportHtml(jasperName, null, null);
			
//			response.setFilename(reportName);
//			response.setByteFile(fileReport);
//			response.setResponseStatus(Status.OK);
//
//			result.add(response);
			
			return Response.ok(fileReport).build();
			
		} catch (Exception e) {
			//throw new DAOException(e);
			throw e;
		}

		//return result;
	}

}

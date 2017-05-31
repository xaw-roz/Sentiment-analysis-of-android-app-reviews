package controller;

import mainClasses.SentimentForFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rocks on 1/30/2017.
 */
@WebServlet(name = "FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "C:\\itdepart\\ITDepartmentApplication\\MiniProjectWeb\\uploadedFiles";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "";
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        filename = name;
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }


            String filepath = "C:\\itdepart\\ITDepartmentApplication\\MiniProjectWeb\\uploadedFiles\\" + filename;


            SentimentForFile sentimentForFile = new SentimentForFile();
            String [] categorizedReviews= sentimentForFile.classifyPositiveAndNegative(filepath, InitiatorListener.reviews);

            String positiveReviews=categorizedReviews[0].split("\\|")[0];
            if(positiveReviews!=null) {
                    positiveReviews = positiveReviews.replaceAll("(\r\n|\n)", ".&#13;&#10;");
            }
            String negativeReviews=categorizedReviews[1].split("\\|")[0];
            if(negativeReviews!=null) {
                    negativeReviews = negativeReviews.replaceAll("(\r\n|\n)", ".&#13;&#10;");
            }

            String undecidableReviews=categorizedReviews[2].split("\\|")[0];
            if(undecidableReviews!=null) {
                    undecidableReviews = undecidableReviews.replaceAll("(\r\n|\n)", ".&#13;&#10;");
            }
            String positive_count=categorizedReviews[0].split("\\|")[1];;
            String negative_count=categorizedReviews[1].split("\\|")[1];
            String undecidable_count=categorizedReviews[2].split("\\|")[1];

            request.setAttribute("positiveReviews",positiveReviews);
            request.setAttribute("negativeReviews",negativeReviews);
            request.setAttribute("undecidableReviews",undecidableReviews);
            request.setAttribute("positiveCount",positive_count);
            request.setAttribute("negativeCount",negative_count);
            request.setAttribute("undecidableCount",undecidable_count);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/showFileSentiment.jsp");
            rd.forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package pe.com.sedapal.common.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;

import eu.bitwalker.useragentutils.Browser;


public class ExcelUtils {
	
	private ExcelUtils() {
	    throw new IllegalStateException("Utility class");
	}

    public static String getDisposition(HttpServletRequest request, String fileName) {
        try {
            Browser browser = AgentUtils.getBrowser(request);

            switch (browser.getGroup()) {
                case IE:
                    return URLEncoder.encode(fileName, ConstantsCommon.UTF8).replaceAll("\\+", "%20");

                case CHROME:
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < fileName.length(); i++) {
                        Character c = fileName.charAt(i);
                        if (c > '~') {
                            sb.append(URLEncoder.encode(c.toString(), ConstantsCommon.UTF8));
                        } else {
                            sb.append(c);
                        }
                    }
                    return sb.toString();

                case OPERA:
                case FIREFOX:
                    return new String(fileName.getBytes(ConstantsCommon.UTF8), "8859_1");
                default:
                	return new String(fileName.getBytes(ConstantsCommon.UTF8), "8859_1");
            }
        } catch (Exception e) {
        	Log.error(e.getMessage());
        }
        return fileName;
    }

    public static void renderExcel(String excelFileName, List<?> dataList, String downloadFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.putVar("dataList", dataList);

        InputStream is = getInputStream(excelFileName);

        response.setCharacterEncoding(ConstantsCommon.UTF8);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader(ConstantsCommon.CONTENT_DISPOSITION, "attachment;filename=" + ExcelUtils.getDisposition(request, downloadFileName + ".xlsx"));

        JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
    }

    public static InputStream getInputStream(String name) throws IOException {
        return new ClassPathResource(name).getInputStream();
    }
}

package fr.lidadi.jee.eventmanager.jsptags;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by damien on 16/10/2016.
 */

public class A extends SimpleTagSupport {

    private String href;
    private String clas;

    private StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        String contextPath = pageContext.getServletContext().getContextPath();
        JspWriter out = getJspContext().getOut();
        href = href == null ? "" : href;
        clas = clas == null ? "" : clas;
        getJspBody().invoke(sw);
        out.println("<a href=\"" + contextPath + href + "\" class=\"" + clas + "\">" + sw + "</a>");
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}

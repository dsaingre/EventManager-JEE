package fr.lidadi.jee.eventmanager.jsptags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by damien on 17/10/2016.
 */
public class Uri extends SimpleTagSupport {

    private String src;

    public void setSrc(String src) {
        this.src = src;
    }


    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        String contextPath = pageContext.getServletContext().getContextPath();
        JspWriter out = getJspContext().getOut();
        src = src == null ? "" : src;
        out.println(contextPath + src);
    }
}

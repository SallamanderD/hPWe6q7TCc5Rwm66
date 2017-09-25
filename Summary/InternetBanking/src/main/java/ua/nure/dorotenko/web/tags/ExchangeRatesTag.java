package ua.nure.dorotenko.web.tags;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.utils.PrivatAPI;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ExchangeRatesTag extends SimpleTagSupport {
    private static final Logger LOG = Logger.getLogger(ExchangeRatesTag.class);

    @Override
    public void doTag() throws JspException, IOException {
        LOG.debug("Tag started");
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter writer = pageContext.getOut();
        StringBuilder sb = new StringBuilder();
        sb.append("<p style=\"margin-left: 2%\" class=\"text-muted\">Exchange Rates: ");
        String rates = PrivatAPI.getExchangeRates();
        LOG.trace("Exchange Rates -> " + rates);
        sb.append(rates);
        sb.append("</p>");
        writer.write(rates);
        LOG.debug("Tag finished");
    }
}

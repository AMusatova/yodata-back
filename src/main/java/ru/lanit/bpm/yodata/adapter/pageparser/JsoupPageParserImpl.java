package ru.lanit.bpm.yodata.adapter.pageparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import us.codecraft.xsoup.XPathEvaluator;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JsoupPageParserImpl implements PageParser {
    @Override
    public String getUrlContent(String url, String xPath) {
        try {
            Document document = Jsoup.connect(url).get();
            return Xsoup.compile(xPath).evaluate(document).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

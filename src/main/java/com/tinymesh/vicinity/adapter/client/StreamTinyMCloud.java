package com.tinymesh.vicinity.adapter.client;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StreamTinyMCloud {

    public void streamTinyM(HttpServletResponse response) throws IOException {

        response.setHeader("Transfer-Encoding", "chunked");


    }
}

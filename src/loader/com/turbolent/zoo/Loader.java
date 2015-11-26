package com.turbolent.zoo;

import com.squareup.okhttp.*;
import com.sun.tools.attach.VirtualMachine;
import jline.console.ConsoleReader;

import java.io.IOException;
import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        String agent = args[0];
        String port = args[1];
        String id = args[2];

        VirtualMachine vm = VirtualMachine.attach(id);
        vm.loadAgent(agent, port);

        OkHttpClient client = new OkHttpClient();

        ConsoleReader reader = new ConsoleReader();
        reader.setBellEnabled(false);

        String line;
        PrintWriter out = new PrintWriter(System.out);

        while ((line = reader.readLine("zoo> ")) != null) {
            String response = request(client, port, line);
            out.println(response);
            out.flush();
        }
    }

    private static String request(OkHttpClient client, String port, String line) throws IOException {
        final MediaType type = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(type, line);
        Request request = new Request.Builder()
            .url(String.format("http://localhost:%s/", port))
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

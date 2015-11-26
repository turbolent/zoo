package com.turbolent.zoo;

import spark.Spark;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Objects;

public class Agent {

    public static void agentmain(String options) throws Exception {
        int port = Integer.valueOf(options);
        Spark.port(port);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        Spark.post("/", (req, res) -> {
            try {
                final Object result = engine.eval(req.body());
                return Objects.toString(result);
            } catch (Exception e) {
                return e.getMessage();
            }
        });

    }
}

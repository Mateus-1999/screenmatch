package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");


        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
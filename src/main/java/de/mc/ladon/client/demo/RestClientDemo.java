package de.mc.ladon.client.demo;

import de.mc.ladon.client.DocumentOrder;
import de.mc.ladon.client.LadonClient;
import de.mc.ladon.client.model.Document;

import java.util.List;
import java.util.UUID;

public class RestClientDemo {

    public static void main(String[] args) {
        LadonClient ladon = LadonClient.connect("http://localhost:8080/", "user", "password");

        int testSize = 10;
        String bucket = UUID.randomUUID().toString();
        System.out.println(bucket);
        ladon.createBucket(bucket);
        for (int i = 0; i < testSize; i++) {
            ladon.createDocument(bucket, "testdoc_" + i + ".txt", ("testdoc_" + i + ".txt").getBytes());
        }
        List<Document> documents = ladon.listAllDocuments(bucket, "", DocumentOrder.name);
        documents.parallelStream().forEach(document ->
        {
            byte[] documentContent = ladon.getDocumentContent(bucket, document.getKey());
            System.out.println(new String(documentContent));
        });
    }
}

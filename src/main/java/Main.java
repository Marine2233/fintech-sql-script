import sql.Script;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Script script = new Script();

        try(ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()){

            CompletableFuture<Void> createDb = CompletableFuture.runAsync(()->{
                try {
                    script.createDB();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },service);

            CompletableFuture<Void>futureAccount = CompletableFuture.runAsync(()->{
                try {
                    script.createAccountTable();
                    script.insertAccount();
                    script.queryAccount();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },service);

            CompletableFuture<Void>futureClient = CompletableFuture.runAsync(()->{
                try {
                    script.createTableClient();
                    script.insertClient();
                    script.queryClient();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },service);

            CompletableFuture<Void>makeNewScript = CompletableFuture.runAsync(()->{
                try {
                    script.queryForFileSqlBaseScript();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },service);

            CompletableFuture.allOf(createDb,futureAccount,futureClient).join();
            CompletableFuture.allOf(makeNewScript).join();
        }
    }
}
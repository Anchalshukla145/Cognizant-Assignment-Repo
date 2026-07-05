public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataById(int id) {
        return externalApi.getDataById(id);
    }

    public void logData(String data) {
        externalApi.logData(data);
    }

}

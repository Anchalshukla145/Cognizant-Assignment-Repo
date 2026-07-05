import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class MyServiceTest {

    @Test
    public void testExternalApi() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.fetchData();
        verify(mockApi).getData();
    }

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getDataById(anyInt())).thenReturn("Matched Data");
        MyService service = new MyService(mockApi);
        String result = service.fetchDataById(123);
        assertEquals("Matched Data", result);
        verify(mockApi).getDataById(eq(123));
    }

    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = mock(ExternalApi.class);
        doNothing().when(mockApi).logData(anyString());
        MyService service = new MyService(mockApi);
        service.logData("test");
        verify(mockApi).logData("test");
    }

    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData())
            .thenReturn("First")
            .thenReturn("Second");
        MyService service = new MyService(mockApi);
        assertEquals("First", service.fetchData());
        assertEquals("Second", service.fetchData());
    }

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.fetchData();
        service.logData("done");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).logData("done");
    }

    @Test
    public void testVoidMethodWithException() {
        ExternalApi mockApi = mock(ExternalApi.class);
        doThrow(new RuntimeException("Error")).when(mockApi).logData(anyString());
        MyService service = new MyService(mockApi);
        assertThrows(RuntimeException.class, () -> {
            service.logData("error");
        });
    }

}

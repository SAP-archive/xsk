package com.sap.xsk.listener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.junit.Before;
import org.junit.Test;

public class NamingResourcesListenerTest {

  public Context ctx;
  public LifecycleEvent lf;
  public NamingResourcesListener namingResourcesListener;

  @Before
  public void setup(){
    namingResourcesListener = new NamingResourcesListener();
    lf = mock(LifecycleEvent.class);
    ctx = mock(Context.class);

  }

  @Test
  public void testLifecycleEventIsContext(){
    when(lf.getLifecycle()).thenReturn(ctx);
    namingResourcesListener.lifecycleEvent(lf);
    verify(ctx, times(1)).getNamingResources();
  }

  @Test
  public void testLifecycleEventNotContext() {
    var notCtx = mock(Lifecycle.class);
    when(lf.getLifecycle()).thenReturn(notCtx);
    namingResourcesListener.lifecycleEvent(lf);
    verify(notCtx, times(0));
  }

}

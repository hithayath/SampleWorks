package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.findus.PayLoad;
import com.du.taskapp.data.findus.ShopResult;
import com.du.taskapp.main.BusProvider;
import com.du.taskapp.network.ApiError;
import com.du.taskapp.network.ApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Hithayath.
 */
public class FindUsPresenterTest {

    @Mock
    ApiService mApiService;

    @Mock
    FindUsView mMockView;

    private FindUsPresenter mTestPresenter;

    @Mock
    ShopResult mTestResponse;

    double mTestLatLong[] = {25.101871, 55.170427}; // Test Data - Salam Tower

    private final List<PayLoad> EMPTY_RESPONSE = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mTestResponse = new ShopResult();
        List<PayLoad> payLoads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PayLoad payLoad = mock(PayLoad.class);
            payLoad.setTitle("Test Title - "+i);
            payLoad.setLatitude((double) i);
            payLoad.setLongitude((double) i);
            payLoads.add(payLoad);
        }
        mTestResponse.setPayLoad(payLoads);
        mTestPresenter = new FindUsPresenterImpl(mApiService);
        mTestPresenter.onAttachView(mMockView);
    }

    @Test
    public void test_loadShops() throws Exception {
        mTestPresenter.loadShops(mTestLatLong);

        verify(mApiService).getFindUsShops();
        BusProvider.getInstance().post(mTestResponse);

        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);
        verify(mMockView).setData(mTestResponse.getPayLoad());
    }

    @Test
    public void test_loadEmptyShops() throws Exception {
        mTestPresenter.loadShops(mTestLatLong);

        verify(mApiService).getFindUsShops();
        mTestResponse.setPayLoad(EMPTY_RESPONSE);
        BusProvider.getInstance().post(mTestResponse);

        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);
        verify(mMockView).setData(mTestResponse.getPayLoad());
    }

    @Test
    public void test_onShopClicked() throws Exception {
        test_loadShops();
    }

    @Test
    public void test_ErrorIsHandled() {

        mTestPresenter.loadShops(mTestLatLong);

        verify(mApiService).getFindUsShops();

        final ApiError error = new ApiError("Test message", new Exception("Test Error"));
        BusProvider.getInstance().post(error);
        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);

        verify(mMockView).showErrorMessageForFailure(error.getMessage());

    }

    @After
    public void tearDown() throws Exception {
        mTestPresenter.onDetachView();
    }

}
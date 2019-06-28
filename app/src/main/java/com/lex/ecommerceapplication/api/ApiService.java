package com.lex.ecommerceapplication.api;

import com.lex.ecommerceapplication.model.response.MasterdataDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * API service for api call.
 *
 * @author DeepS
 */
public interface ApiService
{
    @GET("/json")
	Observable<MasterdataDTO> getCategoryAndProductDetails();
}

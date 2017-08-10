package com.alan.afdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alan.afdemo.R;
import com.alan.afdemo.adapter.MyAdapter;
import com.alan.afdemo.module.ProductItem;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String URL="https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";
    private ArrayList<ProductItem> itemList;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = new ArrayList<>();
        myAdapter=new MyAdapter(itemList,this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        makeRequest(URL);
    }

    public void makeRequest(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {

                try {
                    for (int i = 0; i < response.length(); i++)
                    {
                        JSONObject josonObject = response.getJSONObject(i);
                        ProductItem productItem = new ProductItem();
                        productItem.setBackgroundImage(josonObject.getString("backgroundImage"));
                        productItem.setTitle(josonObject.getString("title"));

                        //check wether element exist, if not, assign empty
                        if(josonObject.has("topDescription"))
                        {
                            productItem.setTopDescription(josonObject.getString("topDescription"));
                        }
                        else
                        {
                            productItem.setTopDescription("");
                        }

                        if(josonObject.has("promoMessage"))
                        {
                            productItem.setPromoMessage(josonObject.getString("promoMessage"));
                        }
                        else
                        {
                            productItem.setPromoMessage("");
                        }

                        if(josonObject.has("bottomDescription"))
                        {
                            productItem.setBottomDescription(josonObject.getString("bottomDescription"));
                        }
                        else
                        {
                            productItem.setBottomDescription("");
                        }


                        if (josonObject.has("content"))
                        {
                            JSONArray jsonContent = josonObject.getJSONArray("content");
                            for(int j=0; j<jsonContent.length();j++)
                            {

                                JSONObject contentObject = jsonContent.getJSONObject(j);

                                if(j==0)
                                {
                                    productItem.setSelectButton_one(contentObject.getString("title"));
                                    productItem.setGetSelectButton_two("");
                                    productItem.setButton_one_link(contentObject.getString("target"));
                                }
                                else
                                {
                                    productItem.setGetSelectButton_two(contentObject.getString("title"));
                                    productItem.setButton_two_link(contentObject.getString("target"));
                                }

                            }

                        }
                        else
                        {
                            productItem.setSelectButton_one("");
                            productItem.setGetSelectButton_two("");
                        }

                        itemList.add(productItem);

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;

                //network status check
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
}

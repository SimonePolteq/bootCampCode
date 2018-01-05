package APItests;

using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ApiDemo
{

    @Test
    public void verstappen2017_returns_200()
    {
        Given().when().get("http://ergast.com/api/f1/2017/drivers/max_verstappen/results.json")
                .then().statusCode(200);
    }    
}


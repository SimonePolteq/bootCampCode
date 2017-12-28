﻿using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace BootCamp.Exercises
{
    [TestClass]
    public class SuccesfulLogin
    {
        [TestMethod]
        public void LogInSuccessFull()
        {
            //open the website
            String Url = "https://techblog.polteq.com/testshop/index.php";
            OpenQA.Selenium.IWebDriver driver = new OpenQA.Selenium.Chrome.ChromeDriver();
            //niet vergeten:
            driver.Manage().Window.Maximize();
            driver.Navigate().GoToUrl(Url);
            //of: driver.URL = <Url>;

            //Click on the login link
            IWebElement LogInLink = driver.FindElement(By.ClassName("login"));
            LogInLink.Click();
            //OF: driver.FindElement(By.ClassName("login")).Click();

            //enter username and password
            IWebElement Email = driver.FindElement(By.Id("email"));
            IWebElement Password = driver.FindElement(By.Id("passwd"));
            Email.SendKeys("simone.russchen@polteq.com");
            Password.SendKeys("bootcamp");

            //Click login button
            IWebElement SubmitLogin = driver.FindElement(By.Id("SubmitLogin"));
            SubmitLogin.Click();

            //validate result
            IWebElement PageHeading = driver.FindElement(By.ClassName("page-heading"));
            String PageHeaderTextActual = PageHeading.GetAttribute("textContent");
            String PageHeaderTextExpected = "My account";
            Assert.AreEqual(PageHeaderTextExpected, PageHeaderTextActual, true, "Check visbility of My Account Page");

            //OF: Verify if the MY ACCOUNT text is shown
            String ValidationString = driver.FindElement(By.CssSelector("h1.page-heading")).Text;
            Assert.AreEqual("MY ACCOUNT", ValidationString, "My account element was not found");

            //OF: Verify if the logout link is idisplayed
            Assert.IsTrue(driver.FindElement(By.CssSelector("a.logout")).Displayed, "Logout link should be displayed");

            //close the browser
            driver.Quit();
        }
    }
}

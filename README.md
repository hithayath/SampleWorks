
Overview:
=========

 - Supported versions : 16[OS - 4.1 JB] - 26[OS - 8.0 Oreo]

 - Libraries :
	1. Android support libraries like appcompat, recyclerview and design.
	2. ButterKnife - View Binding.
	3. Glide - Image loading and decoding.
	4. Otto - Event bus.
	5. Maps - Google play services.
	6. Location - Smart location from io.nlopez.smartlocation.
	7. Retrofit with okhttp3, gson - Network communication and data parsing.
	8. Unit testing - JUnit, Mockito.

Usage:
======
1. Clone/Download from github link.
2. Open from AndroidStudio as  "open an existing Android Studio project" at cloned/extracted drive location.
3. Find below the project structure explanation for details.
	
Project Structure:
==================

Design pattern : MVP

Base package : com.du.taskapp

Depends on the functionaly, components are categorized as sub-packages as below:

 - data : Separate Data model for home and findus screens generated from the JSON response.

 - main : MainApplication and BusProvider.

 - network : 
	ApiInterface - To define APIs to be used by HTTP client.
  
	ApiService - Used by presenter to do network communication and achieve data for the UI.
				- Used by tester to mock the above said functionality.
        
	ApiServiceImpl - HTTP Client for actual communication.
  
	ApiCallback - Callbacks used in HTTP Client.
  
	ApiError - Provides api error information.
	

 - permission:
 
	RuntimePermissionController - From 6.0 OS, User have to give permission for the first time to achieve location details.

 - ui:
	- mvp:
  
		FindUsPresenter,
		FindUsPresenterImpl : Used by FindUs screen to achieve functionality as follows,
		- Loading of shop list.
		- Click event to show up map view.

		HomePresenter,
		HomePresenterImpl: Used by Home Screen to achieve functionality as follows,
		- Loading of home screen data and filter based on active and visible data.
		- Click event to show up Web view or FindUs screen.
		
		FindUsView,
		HomeView : Interface used by respective presenters to update UI and view mocking in unit test.
		
	FindUsActivity: To represent FindUs Screen.
  
	FindUsShopsAdapter: To be used by recyclerview to show up shop-list in linear view.

	HomeActivity: To represent Home Screen.
  
	HomeAdapter: To be used by recyclerview to show up home data in grid view.
  
	SpacesItemDecoration: To used by recyclerview for home screen grid view.
	
	MapsActivity: To present mapview using coordinates from shop-list.
	
	WebViewActivity: To present webview content from home screen with external link except FindUs.
	
 - util:
 
	DistanceCalculator : To calculate distance in "KM" using location details.

Unit Test:
==========
 - ui-mvp:
 
	FindUsPresenterTest : To unit test FindUsPresenter functionality.
  
	HomePresenterTest: To unit test HomePresenter functionality. 
	
Note : 
1. Data caching for offline is not handled, but can be achieved using Realm in existing data model.


Demo:
=====

![demo](https://user-images.githubusercontent.com/13219351/32593234-9826912a-c540-11e7-8d13-de76597b5169.gif)

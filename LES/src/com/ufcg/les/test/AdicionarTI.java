package com.ufcg.les.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class AdicionarTI extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ufcg.les.Login";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public AdicionarTI() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
		// Wait for activity: 'com.ufcg.les.Login'
		solo.waitForActivity("Login", 2000);
		// Click on Continar
		solo.clickOnView(solo.getView("buttonContinuar"));
		// Wait for activity: 'com.ufcg.les.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
		// Click on Adicionar TI
		solo.clickOnView(solo.getView("Button_AddTI"));
		// Wait for activity: 'com.ufcg.les.AdicionarTIActivity'
		assertTrue("AdicionarTIActivity is not found!", solo.waitForActivity("AdicionarTIActivity"));
		// Click on Empty Text View
		solo.clickOnView(solo.getView("name_ti"));
		// Enter the text: 'Robotium'
		solo.clearEditText((android.widget.EditText) solo.getView("name_ti"));
		solo.enterText((android.widget.EditText) solo.getView("name_ti"), "Robotium");
		// Enter the text: '2'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "2");
		// Click on Ok
		solo.clickOnView(solo.getView("botaoOK"));
		// Wait for activity: 'com.ufcg.les.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
		// Click on Adicionar TI
		solo.clickOnView(solo.getView("Button_AddTI"));
		// Wait for activity: 'com.ufcg.les.AdicionarTIActivity'
		assertTrue("AdicionarTIActivity is not found!", solo.waitForActivity("AdicionarTIActivity"));
		// Click on Empty Text View
		solo.clickOnView(solo.getView("name_ti"));
		// Enter the text: 'TestRobotium'
		solo.clearEditText((android.widget.EditText) solo.getView("name_ti"));
		solo.enterText((android.widget.EditText) solo.getView("name_ti"), "TestRobotium");
		// Enter the text: '1'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "1");
		// Click on Ok
		solo.clickOnView(solo.getView("botaoOK"));
		// Wait for activity: 'com.ufcg.les.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
		// Press menu back key
		solo.goBack();
		// Press menu back key
		solo.goBack();
	}
}

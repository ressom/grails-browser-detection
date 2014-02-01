package org.geeks.browserdetection

import grails.test.mixin.TestFor
import org.geeks.browserdetection.ComparisonType

import org.springframework.web.context.request.RequestContextHolder as RCH

@TestFor(UserAgentIdentService)
class UserAgentIdentServiceTests extends GroovyTestCase {

    void testFirefox3_6_9() {
	    RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9 ( .NET CLR 3.5.30729; .NET CLR 4.0.20506)")

	    assert service.isFirefox()
	    assert !service.isChrome()
	    assert !service.isSafari()
	    assert !service.isiOsDevice()
	    assert !service.isMobile()
	    assert service.getBrowser() == "Firefox 3"
	    assert service.getBrowserVersion() == "3.6.9"
	    assert !service.isOther()
    }

	void testChrome14_0_835_202() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1")

	    assert !service.isFirefox()
	    assert service.isChrome()
		assert !service.isSafari()
	    assert !service.isiOsDevice()
	    assert !service.isMobile()
		assert service.getBrowser() == "Chrome 14"
	    assert service.getBrowserVersion() == "14.0.835.202"
		assert !service.isOther()
	}

	void testMSIE7() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; GTB6.4; .NET CLR 1.1.4322; FDM; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)")

	    assert !service.isFirefox()
	    assert !service.isChrome()
		assert !service.isSafari()
		assert service.isMsie()
	    assert !service.isiOsDevice()
	    assert !service.isMobile()
		assert service.getBrowser() == "Internet Explorer 7"
	    assert service.getBrowserVersion() == "7.0"
	}

	void testMSIE6() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98; Rogers Hi·Speed Internet; (R1 1.3))")

	    assert !service.isFirefox()
	    assert !service.isChrome()
		assert !service.isSafari()
		assert service.isMsie()
	    assert !service.isiOsDevice()
	    assert !service.isMobile()
		assert service.getBrowser() == "Internet Explorer 6"
	    assert service.getBrowserVersion() == "6.0"
	}

	void testIPadSafari4_0_4() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0(iPad; U; CPU iPhone OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B314 Safari/531.21.10")

	    assert !service.isFirefox()
	    assert !service.isChrome()
		assert service.isSafari()
		assert !service.isMsie()
	    assert service.isiOsDevice()
		assert service.isiPad()
	    assert service.isMobile()
		assert service.getBrowser() == "Mobile Safari"
	    assert service.getBrowserVersion() == "4.0.4"
	}

	void testIPhoneSafari4_0_4() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (iPhone; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10")

	    assert !service.isFirefox()
	    assert !service.isChrome()
		assert service.isSafari()
		assert !service.isMsie()
	    assert service.isiOsDevice()
		assert service.isiPhone()
	    assert service.isMobile()
		assert service.getBrowser() == "Mobile Safari"
	    assert service.getBrowserVersion() == "4.0.4"
	}

	void testChrome14_0_835_202andVersionChecking() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1")

	    assert service.isChrome()
		assert service.isChrome(ComparisonType.EQUAL, "14.0.835.202")
	    assert service.isChrome(ComparisonType.GREATER, "13.0.835")
		assert service.isChrome(ComparisonType.LOWER, "15")

	}

	void testFirefox9_0andVersionChecking() {
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:9.0) Gecko/20100101 Firefox/9.0")

	    assert service.isFirefox()
		assert service.isFirefox(ComparisonType.EQUAL, "9.0")
	    assert service.isFirefox(ComparisonType.GREATER, "8.0")
		assert service.isFirefox(ComparisonType.LOWER, "10.10")

	}

	void testOldApi(){
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:9.0) Gecko/20100101 Firefox/9.0")

		assert service.getBrowserType() == UserAgentIdentService.CLIENT_FIREFOX
		assert service.getBrowserName() == UserAgentIdentService.FIREFOX
	}

	void testIsMobileIPhone(){
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; ja-jp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5")

		assert service.isMobile()
	}

	void testIsMobileBlackberry(){
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; zh-TW) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.246 Mobile Safari/534.1+")

		assert service.isMobile()
	}

	void testIsMobileAndroid(){
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "Mozilla/5.0 (Linux; U; Android 2.3; en-us) AppleWebKit/999+ (KHTML, like Gecko) Safari/999.9")

		assert service.isMobile()
	}

	/**
	 * Tests for proper handling of the case of unset user-agent header
	 */
	void testNullUserAgentHeader(){

		assert RCH.currentRequestAttributes().currentRequest.getHeader("user-agent") == null
		assert !service.isMobile()

		assert service.isOther()
	}

	void testIsOther(){
		RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
	        "abs; abc")

		assert service.isOther()
		assert !service.isChrome()
		assert !service.isMobile()
	}
    
    void testBreaksWithNPE() {
        RCH.currentRequestAttributes().currentRequest.addHeader("user-agent",
            "Mozilla/4.0 (compatible; MSIE; Windows NT 5.1; GTB6.4; .NET CLR 1.1.4322; FDM; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)")

        // version string would normally contain things like "MSIE #.#;" ... but when version number is missing, NPE results
        try {
            service.isMsie(ComparisonType.EQUAL, "7.*") // <-- should fail with NPE ... cannot test since I cannot get the project to run ... some config and/or dependency issue(s)
            fail("invocation did not fail as expected")
        } catch (NullPointerException e) { /* ignored */ }
    }
    
}
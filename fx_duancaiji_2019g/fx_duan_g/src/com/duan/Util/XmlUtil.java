package com.duan.Util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.duan.domain.VCollectfield;

public class XmlUtil {

	private static String xmlUrl;

	public static void generateXML(VCollectfield fieldCollect, String resoledMessage, Element root) {

		List<Element> comlist = root.selectNodes("com[@id='" + fieldCollect.getComName() + "']");
		Element comElement = null;
		if (comlist.size() <= 0) {
			comElement = root.addElement("com");
			comElement.addAttribute("id", fieldCollect.getComName());
		} else {
			comElement = comlist.get(0);
		}

		List<Element> meterlist = comElement.selectNodes("meter[@id='" + fieldCollect.getMeterid() + "']");
		Element meterElement = null;
		if (meterlist.size() <= 0) {
			meterElement = comElement.addElement("meter");
			meterElement.addAttribute("name", fieldCollect.getMeterName());
			meterElement.addAttribute("id", fieldCollect.getMeterid());
		} else {
			meterElement = meterlist.get(0);
		}

		Element funElement = meterElement.addElement("function");
		funElement.addAttribute("name", fieldCollect.getFieldName());
		funElement.addAttribute("id", fieldCollect.getFieldId());
		funElement.addText(resoledMessage);

	}

	public static void generateXMLopc(VCollectfield fieldCollect, String resoledMessage, Element root) {

		List<Element> comlist = root.selectNodes("opc[@id='" + fieldCollect.getComName() + "']");
		Element comElement = null;
		if (comlist.size() <= 0) {
			comElement = root.addElement("opc");
			//comElement.addAttribute("id", fieldCollect.getComName());
		} else {
			comElement = comlist.get(0);
		}

		List<Element> meterlist = comElement.selectNodes("shbei[@id='" + fieldCollect.getMeterid() + "']");
		Element meterElement = null;
		if (meterlist.size() <= 0) {
			meterElement = comElement.addElement("shebei");
			meterElement.addAttribute("name", fieldCollect.getMeterName());
			//meterElement.addAttribute("id", fieldCollect.getMeterid());
		} else {
			meterElement = meterlist.get(0);
		}

		Element funElement = meterElement.addElement("function");
		//funElement.addAttribute("name", fieldCollect.getFieldName());
		//funElement.addAttribute("id", fieldCollect.getFieldId());
		funElement.addText(resoledMessage);

	}


	public static String getXmlUrl() {
		return xmlUrl;
	}

	public static void setXmlUrl(String xmlUrl) {
		XmlUtil.xmlUrl = xmlUrl;
	}

}

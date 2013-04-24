package edu.chip.i2b2ssr.admin

import scala.xml.NodeSeq
import scala.xml.Utility
import scala.xml.XML

/**
 * @author David Ortiz
 * @date 4/22/13
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */
class TestUtil {

    static def NodeSeq loadScalaXml(String xml){
        def newXml = Utility.trim(XML.loadString(xml))
        return newXml

    }
}

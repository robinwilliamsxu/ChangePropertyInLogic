/*
 * @(#)ChangeProperty.java   09/09/03
 *
 * Copyright (c) 2009 Mentor Graphics Corporation.  All Rights Reserved.
 * <p>
 * Recipients who obtain this code directly from Mentor Graphics use it solely
 * for internal purposes to serve as example Java or Java Script plugins.
 * This code may not be used in a commercial distribution. Recipients may
 * duplicate the code provided that all notices are fully reproduced with
 * and remain in the code. No part of this code may be modified, reproduced,
 * translated, used, distributed, disclosed or provided to third parties
 * without the prior written consent of Mentor Graphics, except as expressly
 * authorized above.
 * <p>
 * THE CODE IS MADE AVAILABLE "AS IS" WITHOUT WARRANTY OR SUPPORT OF ANY KIND.
 * MENTOR GRAPHICS OFFERS NO EXPRESS OR IMPLIED WARRANTIES AND SPECIFICALLY
 * DISCLAIMS ANY WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * OR WARRANTY OF NON-INFRINGEMENT. IN NO EVENT SHALL MENTOR GRAPHICS OR ITS
 * LICENSORS BE LIABLE FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING LOST PROFITS OR SAVINGS) WHETHER BASED ON CONTRACT, TORT
 * OR ANY OTHER LEGAL THEORY, EVEN IF MENTOR GRAPHICS OR ITS LICENSORS HAVE BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * <p>
 */



//~--- non-JDK imports --------------------------------------------------------

/**
 * CONTENTS: Open a file using the default handler from a property called FILE
 *
 * @author Mike Stamper
 * @date August 28, 2009
 */
import com.mentor.chs.api.IXWire;
import com.mentor.chs.plugin.IXApplicationContext;
import com.mentor.chs.plugin.IXAttributeSetter;
import com.mentor.chs.plugin.IXOutputWindow;
import com.mentor.chs.plugin.action.IXHarnessAction;
import com.mentor.chs.plugin.action.IXIntegratorAction;
import com.mentor.chs.plugin.action.IXLogicAction;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Desktop;


import java.util.Set;

import javax.swing.Icon;

public class ChangeProperty implements IXLogicAction, IXIntegratorAction, IXHarnessAction
    {
    IXApplicationContext cntx;
    public String[] props = { "TPS", "Document" };
    public String   OldProperty = "";
    public String   NewProperty = "";
    public String   PropertyValue = "";
    public Desktop  desktop;

    
    

    public boolean execute(IXApplicationContext applicationContext) {

        cntx = applicationContext;
        cntx.getOutputWindow().clear();
        // Open PopUp Dialog
//        PopMe dialog = new PopMe(new javax.swing.JFrame(), true);
//
//        dialog.pack();
//        dialog.setLocationRelativeTo(null);
//        dialog.setVisible(true);
//        OldProperty = dialog.retValue.toString();
//        NewProperty= dialog.newValue.toString();
//         Iterator i$;
//        // IXConnector conn;
         Set<IXWire> Connlist = cntx.getCurrentDesign().getConnectivity().getWires();
         for(IXWire conn: Connlist)
            {
            IXOutputWindow outputWindow = cntx.getOutputWindow();
            final IXAttributeSetter attributeSetter = conn.getAttributeSetter();
            if (attributeSetter == null) 
            {
                outputWindow.println("ERROR: not in read/write mode. Can't change: " + conn);
                return false;
            }
            PropertyValue = conn.getProperty(OldProperty);
            //if(conn.getProperty(OldProperty)!=null)
                {
                    attributeSetter.addProperty("ss","ss");
                    //attributeSetter.removeProperty(OldProperty);
                    //attributeSetter.addProperty(NewProperty, PropertyValue);
                }
        
            
            outputWindow.println(conn.getAttribute("Name")+":"+"   Property:"+OldProperty+"has been removed"+"    Property:"+NewProperty+"has been added");
            }
         return true;
    }
 public String getVersion() {
        return "0.111114";
    }
    public String getLongDescription() {
        return "Launch file using default handler from perperty called FILE from selected object";
    }

    public Integer getMnemonicKey() {
        return null;
    }

    public Icon getSmallIcon() {
        return null;
    }

    public boolean isReadOnly() {
        return false;
    }

    public boolean isAvailable(IXApplicationContext applicationContext) {
        return true;
    }

    public Trigger[] getTriggers() {
        return new Trigger[] { Trigger.ContextMenu, Trigger.MainMenu };
    }

    public String getDescription() {
        return "change all property";
    }

    public String getName() {
        return "Change Property Name In logic";
    }

   
    }

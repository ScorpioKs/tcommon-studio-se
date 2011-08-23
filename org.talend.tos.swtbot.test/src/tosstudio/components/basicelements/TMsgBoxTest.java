package tosstudio.components.basicelements;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.talend.swtbot.TalendSwtBotForTos;
import org.talend.swtbot.Utilities;
import org.talend.swtbot.helpers.JobHelper;
import org.talend.swtbot.items.TalendJobItem;

// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

/**
 * DOC Administrator class global comment. Detailled comment
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TMsgBoxTest extends TalendSwtBotForTos {

    private TalendJobItem jobItem;

    private static final String JOBNAME = "tMsgBoxTesting"; //$NON-NLS-1$

    @Before
    public void createJob() {
        jobItem = new TalendJobItem(JOBNAME);
        jobItem.create();
    }

    @Test
    public void useComponentInJob() {
        SWTBotGefEditor jobEditor = jobItem.getJobEditor();

        Utilities.dndPaletteToolOntoJob(jobEditor, "tMsgBox", new Point(100, 100));
        SWTBotGefEditPart msgBox = getTalendComponentPart(jobEditor, "tMsgBox_1");
        Assert.assertNotNull("can not get component 'tMsgBox'", msgBox);

        jobEditor.save();

        /* Run the job */
        JobHelper.runJob(jobEditor);
    }

    @After
    public void removePreviousCreateItems() {
        jobItem.getJobEditor().saveAndClose();
        Utilities.delete(jobItem.getParentNode(), JOBNAME, "0.1", null);
        Utilities.emptyRecycleBin();
    }
}

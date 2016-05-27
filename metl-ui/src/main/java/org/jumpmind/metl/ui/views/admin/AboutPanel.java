/**
 * Licensed to JumpMind Inc under one or more contributor
 * license agreements.  See the NOTICE file distributed
 * with this work for additional information regarding
 * copyright ownership.  JumpMind Inc licenses this file
 * to you under the GNU General Public License, version 3.0 (GPLv3)
 * (the "License"); you may not use this file except in compliance
 * with the License.
 *
 * You should have received a copy of the GNU General Public License,
 * version 3.0 (GPLv3) along with this library; if not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jumpmind.metl.ui.views.admin;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.jumpmind.metl.core.runtime.AgentManager;
import org.jumpmind.metl.core.util.VersionUtils;
import org.jumpmind.metl.ui.common.ApplicationContext;
import org.jumpmind.metl.ui.common.TabbedPanel;
import org.jumpmind.metl.ui.common.Table;
import org.jumpmind.util.AppUtils;
import org.jumpmind.vaadin.ui.common.CommonUiUtils;
import org.jumpmind.vaadin.ui.common.IUiPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.VerticalLayout;

public class AboutPanel extends VerticalLayout implements IUiPanel {

    final Logger log = LoggerFactory.getLogger(getClass());

    private static final long serialVersionUID = 1L;

    Table table;

    public AboutPanel(ApplicationContext context, TabbedPanel tabbedPanel) {
        setSizeFull();
        setMargin(true);
    }

    @Override
    public boolean closing() {
        return true;
    }

    @Override
    public void deselected() {
    }

    @Override
    public void selected() {
        if (table != null) {
            removeComponent(table);
        }
        table = new Table();
        table.setSizeFull();
        table.addStyleName("noscroll");
        table.addContainerProperty("Name", String.class, null);
        table.setColumnWidth("Name", 200);
        table.addContainerProperty("Value", String.class, null);

        int itemId = 0;
        table.addItem(new Object[] { "Application Version", VersionUtils.getCurrentVersion() }, itemId++);
        table.addItem(new Object[] { "Build Time", VersionUtils.getBuildTime() }, itemId++);
        table.addItem(new Object[] { "SCM Revision", VersionUtils.getScmVersion() }, itemId++);
        table.addItem(new Object[] { "SCM Branch", VersionUtils.getScmBranch() }, itemId++);

        table.addItem(new Object[] { "Host Name", AppUtils.getHostName() }, itemId++);
        table.addItem(new Object[] { "IP Address", AppUtils.getIpAddress() }, itemId++);
        table.addItem(new Object[] { "Java Version", System.getProperty("java.version") }, itemId++);
        table.addItem(new Object[] { "System Time", FastDateFormat.getTimeInstance(FastDateFormat.MEDIUM).format(new Date()) }, itemId++);
        table.addItem(new Object[] { "Used Heap", Long.toString(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) },
                itemId++);
        table.addItem(new Object[] { "Heap Size", Long.toString(Runtime.getRuntime().maxMemory()) }, itemId++);
        table.addItem(new Object[] { "Last Restart", CommonUiUtils.formatDateTime(AgentManager.lastRestartTime) }, itemId++);

        addComponent(table);
        setExpandRatio(table, 1);

    }

}

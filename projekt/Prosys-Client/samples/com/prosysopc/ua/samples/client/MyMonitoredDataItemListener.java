/**
 * Prosys OPC UA Java SDK
 *
 * Copyright (c) 2009-2010 Prosys PMS Ltd., <http://www.prosysopc.com>. 
 * All rights reserved.
 */
package com.prosysopc.ua.samples.client;

import org.opcfoundation.ua.builtintypes.DataValue;

import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;

/**
 * A sampler listener for monitored data changes.
 */
public class MyMonitoredDataItemListener implements MonitoredDataItemListener {
	private final SampleConsoleClient client;

	/**
	 * @param client
	 */
	public MyMonitoredDataItemListener(SampleConsoleClient client) {
		super();
		this.client = client;
	}

	@Override
	public void onDataChange(MonitoredDataItem sender, DataValue prevValue,
			DataValue value) {
		SampleConsoleClient.println(client.dataValueToString(
				sender.getNodeId(), sender.getAttributeId(), value));
	}
};

/* File modified by S2H Mobile.
 * 
 * Copyright 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.s2hmobile.compat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import de.s2hmobile.compat.actionbar.ActionBarConfigurator;
import de.s2hmobile.compat.actionbar.ActionBarHelper;

/**
 * A base activity that defers all ActionBar functionality across derived
 * activities to an {@link ActionBarHelper}. It extends
 * {@link android.support.v4.app.FragmentActivity}, so fragments can be used.
 */
public abstract class ActionBarFragmentActivity extends FragmentActivity
		implements ActionBarConfigurator {

	/**
	 * The {@link ActionBarHelper} instance. Instantiated with a static call to
	 * {@link ActionBarHelper#createInstance(Activity, boolean)}. Arguments are
	 * the base activity and the boolean flag as set by the callback.
	 */
	private final ActionBarHelper mActionBarHelper = ActionBarHelper
			.createInstance(ActionBarFragmentActivity.this, isHomeStateful(),
					setHomeResId());

	/**
	 * Returns the {@link ActionBarHelper} for this
	 * {@link ActionBarFragmentActivity}.
	 */
	public ActionBarHelper getActionBarHelper() {
		return mActionBarHelper;
	}

	/** {@inheritDoc} */
	@Override
	public MenuInflater getMenuInflater() {
		return mActionBarHelper.getMenuInflater(super.getMenuInflater());
	}

	/**
	 * Base action bar-aware implementation for
	 * {@link Activity#onCreateOptionsMenu(android.view.Menu)}.
	 * 
	 * Note: marking menu items as invisible/visible is not currently supported.
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		boolean retValue = false;
		retValue |= mActionBarHelper.onCreateOptionsMenu(menu);
		retValue |= super.onCreateOptionsMenu(menu);
		return retValue;
	}

	/** {@inheritDoc} */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		mActionBarHelper.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
	}

	/** {@inheritDoc} */
	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActionBarHelper.onPostCreate(savedInstanceState);
	}

	/** {@inheritDoc} */
	@Override
	protected void onTitleChanged(final CharSequence title, final int color) {
		mActionBarHelper.onTitleChanged(title, color);
		super.onTitleChanged(title, color);
	}
}
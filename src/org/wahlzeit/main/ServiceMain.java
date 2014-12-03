/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.main;

import org.wahlzeit.agents.AgentManager;
import org.wahlzeit.handlers.AdminUserProfileFormHandler;
import org.wahlzeit.handlers.ChangePasswordFormHandler;
import org.wahlzeit.handlers.ConfirmAccountPageHandler;
import org.wahlzeit.handlers.EditPhotoCaseFormHandler;
import org.wahlzeit.handlers.EditUserPhotoFormHandler;
import org.wahlzeit.handlers.EditUserProfileFormHandler;
import org.wahlzeit.handlers.EmailPasswordFormHandler;
import org.wahlzeit.handlers.EmailUserNameFormHandler;
import org.wahlzeit.handlers.FilterPhotosFormHandler;
import org.wahlzeit.handlers.FilterPhotosPageHandler;
import org.wahlzeit.handlers.FlagPhotoFormHandler;
import org.wahlzeit.handlers.LoginFormHandler;
import org.wahlzeit.handlers.LogoutPageHandler;
import org.wahlzeit.handlers.NullFormHandler;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.PraisePhotoFormHandler;
import org.wahlzeit.handlers.ResetSessionPageHandler;
import org.wahlzeit.handlers.SendEmailFormHandler;
import org.wahlzeit.handlers.SetLanguagePageHandler;
import org.wahlzeit.handlers.SetOptionsFormHandler;
import org.wahlzeit.handlers.SetPhotoSizePageHandler;
import org.wahlzeit.handlers.ShowAdminPageHandler;
import org.wahlzeit.handlers.ShowInfoPageHandler;
import org.wahlzeit.handlers.ShowNotePageHandler;
import org.wahlzeit.handlers.ShowPartPageHandler;
import org.wahlzeit.handlers.ShowPhotoCasesPageHandler;
import org.wahlzeit.handlers.ShowPhotoPageHandler;
import org.wahlzeit.handlers.ShowUserHomePageHandler;
import org.wahlzeit.handlers.ShowUserProfileFormHandler;
import org.wahlzeit.handlers.SignupFormHandler;
import org.wahlzeit.handlers.TellFriendFormHandler;
import org.wahlzeit.handlers.WebPartHandler;
import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.handlers.extension.AdminUserPancakePhotoFormHandler;
import org.wahlzeit.handlers.extension.PancakePartUtil;
import org.wahlzeit.handlers.extension.ShowUserPancakePhotoFormHandler;
import org.wahlzeit.handlers.extension.UploadPancakePhotoFormHandler;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.EnglishModelConfig;
import org.wahlzeit.model.GermanModelConfig;
import org.wahlzeit.model.LanguageConfigs;
import org.wahlzeit.services.ConfigDir;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.webparts.WebPartTemplateService;

/**
 * A Main class that runs a Wahlzeit web server.
 * 
 * @author dirkriehle
 *
 */
public class ServiceMain extends ModelMain {
	
	/**
	 * 
	 */
	protected static ServiceMain instance = new ServiceMain();

	/**
	 * 
	 */
	protected boolean isToStop = false;

	/**
	 * 
	 */
	protected boolean isInProduction = false;
	
	/**
	 * 
	 */
	public static ServiceMain getInstance() {
		return instance;
	}
	
	/**
	 * 
	 */
	public void requestStop() {
		synchronized(instance) {
			instance.isToStop = true;
		}
	}
	
	/**
	 * 
	 */
	public boolean isShuttingDown() {
		return instance.isToStop;
	}
		
	/**
	 * 
	 */
	public boolean isInProduction() {
		return instance.isInProduction;
	}
	
	/**
	 * 
	 */
	public void startUp(boolean inProduction, String rootDir) throws Exception {
		isInProduction = inProduction;
		
		super.startUp(rootDir);

		configureWebPartTemplateService();
		configureWebPartHandlers();
		configureLanguageModels();

		AgentManager am = AgentManager.getInstance();
		am.startAllThreads();
	}
	
	/**
	 * 
	 */
	public void shutDown() throws Exception {
		AgentManager am = AgentManager.getInstance();
		am.stopAllThreads();
				
		super.shutDown();
	}
	
	/**
	 * 
	 */
	public void configureWebPartTemplateService() {
		ConfigDir templatesDir = SysConfig.getTemplatesDir();
		WebPartTemplateService.getInstance().setTemplatesDir(templatesDir);
	}
	
	/**
	 * 
	 */
	public void configureWebPartHandlers() {
		WebPartHandler temp = null;
		WebPartHandlerManager manager = WebPartHandlerManager.getInstance();
		
		// NullInfo and NullForm
		manager.addWebPartHandler(PancakePartUtil.NULL_FORM_NAME, new NullFormHandler());
		
		// Note page
		manager.addWebPartHandler(PancakePartUtil.SHOW_NOTE_PAGE_NAME, new ShowNotePageHandler());

		// ShowPhoto page
		manager.addWebPartHandler(PancakePartUtil.FILTER_PHOTOS_FORM_NAME, new FilterPhotosFormHandler());
		manager.addWebPartHandler(PancakePartUtil.PRAISE_PHOTO_FORM_NAME, new PraisePhotoFormHandler());

		temp = new ShowPhotoPageHandler();
		manager.addWebPartHandler(PancakePartUtil.SHOW_PHOTO_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.ENGAGE_GUEST_FORM_NAME, temp);
		
		manager.addWebPartHandler(PancakePartUtil.FILTER_PHOTOS_PAGE_NAME, new FilterPhotosPageHandler());

		manager.addWebPartHandler(PancakePartUtil.RESET_SESSION_PAGE_NAME, new ResetSessionPageHandler());
		
		// About and Terms pages
		manager.addWebPartHandler(PancakePartUtil.ABOUT_PAGE_NAME, new ShowInfoPageHandler(AccessRights.GUEST, PartUtil.ABOUT_INFO_FILE));
		manager.addWebPartHandler(PancakePartUtil.CONTACT_PAGE_NAME, new ShowInfoPageHandler(AccessRights.GUEST, PartUtil.CONTACT_INFO_FILE));
		manager.addWebPartHandler(PancakePartUtil.IMPRINT_PAGE_NAME, new ShowInfoPageHandler(AccessRights.GUEST, PartUtil.IMPRINT_INFO_FILE));
		manager.addWebPartHandler(PancakePartUtil.TERMS_PAGE_NAME, new ShowInfoPageHandler(AccessRights.GUEST, PartUtil.TERMS_INFO_FILE));

		// Flag, Send, Tell, and Options pages
		temp = manager.addWebPartHandler(PancakePartUtil.FLAG_PHOTO_FORM_NAME, new FlagPhotoFormHandler());
		manager.addWebPartHandler(PancakePartUtil.FLAG_PHOTO_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.SEND_EMAIL_FORM_NAME, new SendEmailFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SEND_EMAIL_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.TELL_FRIEND_FORM_NAME, new TellFriendFormHandler());
		manager.addWebPartHandler(PancakePartUtil.TELL_FRIEND_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.SET_OPTIONS_FORM_NAME, new SetOptionsFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SET_OPTIONS_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		
		// Signup, Login, EmailUserName/Password, and Logout pages
		temp = manager.addWebPartHandler(PancakePartUtil.SIGNUP_FORM_NAME, new SignupFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SIGNUP_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));

		manager.addWebPartHandler(PancakePartUtil.CONFIRM_ACCOUNT_PAGE_NAME, new ConfirmAccountPageHandler());

		temp = manager.addWebPartHandler(PancakePartUtil.LOGIN_FORM_NAME, new LoginFormHandler());
		manager.addWebPartHandler(PancakePartUtil.LOGIN_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.EMAIL_USER_NAME_FORM_NAME, new EmailUserNameFormHandler());
		manager.addWebPartHandler(PancakePartUtil.EMAIL_USER_NAME_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.EMAIL_PASSWORD_FORM_NAME, new EmailPasswordFormHandler());
		manager.addWebPartHandler(PancakePartUtil.EMAIL_PASSWORD_PAGE_NAME, new ShowPartPageHandler(AccessRights.GUEST, temp));

		manager.addWebPartHandler(PancakePartUtil.LOGOUT_PAGE_NAME, new LogoutPageHandler());
		
		// SetLanguage pages
		temp = new SetLanguagePageHandler();
		manager.addWebPartHandler(PancakePartUtil.SET_ENGLISH_LANGUAGE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_GERMAN_LANGUAGE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_SPANISH_LANGUAGE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_JAPANESE_LANGUAGE_PAGE_NAME, temp);

		// SetPhotoSize pages
		temp = new SetPhotoSizePageHandler();
		manager.addWebPartHandler(PancakePartUtil.SET_EXTRA_SMALL_PHOTO_SIZE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_SMALL_PHOTO_SIZE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_MEDIUM_PHOTO_SIZE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_LARGE_PHOTO_SIZE_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SET_EXTRA_LARGE_PHOTO_SIZE_PAGE_NAME, temp);

		// ShowHome page
		manager.addWebPartHandler(PancakePartUtil.SHOW_USER_PROFILE_FORM_NAME, new ShowUserProfileFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SHOW_USER_PANCAKEPHOTO_FORM_NAME, new ShowUserPancakePhotoFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SHOW_USER_HOME_PAGE_NAME, new ShowUserHomePageHandler());
		
		// EditProfile, ChangePassword, EditPhoto, and UploadPhoto pages
		temp = manager.addWebPartHandler(PancakePartUtil.EDIT_USER_PROFILE_FORM_NAME, new EditUserProfileFormHandler());
		manager.addWebPartHandler(PancakePartUtil.EDIT_USER_PROFILE_PAGE_NAME, new ShowPartPageHandler(AccessRights.USER, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.CHANGE_PASSWORD_FORM_NAME, new ChangePasswordFormHandler());
		manager.addWebPartHandler(PancakePartUtil.CHANGE_PASSWORD_PAGE_NAME, new ShowPartPageHandler(AccessRights.USER, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.EDIT_USER_PHOTO_FORM_NAME, new EditUserPhotoFormHandler());
		manager.addWebPartHandler(PancakePartUtil.EDIT_USER_PHOTO_PAGE_NAME, new ShowPartPageHandler(AccessRights.USER, temp));
		temp = manager.addWebPartHandler(PancakePartUtil.UPLOAD_PANCAKEPHOTO_FORM_NAME, new UploadPancakePhotoFormHandler());
		manager.addWebPartHandler(PancakePartUtil.UPLOAD_PHOTO_PAGE_NAME, new ShowPartPageHandler(AccessRights.USER, temp));
		
		manager.addWebPartHandler(PancakePartUtil.EDIT_PHOTO_CASE_FORM_NAME, new EditPhotoCaseFormHandler());
		manager.addWebPartHandler(PancakePartUtil.SHOW_PHOTO_CASES_PAGE_NAME, new ShowPhotoCasesPageHandler());

		// Admin page incl. AdminUserProfile and AdminUserPhoto
		temp = new ShowAdminPageHandler();
		manager.addWebPartHandler(PancakePartUtil.SHOW_ADMIN_PAGE_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.SHOW_ADMIN_MENU_FORM_NAME, temp);
		manager.addWebPartHandler(PancakePartUtil.ADMIN_USER_PROFILE_FORM_NAME, new AdminUserProfileFormHandler());
		manager.addWebPartHandler(PancakePartUtil.ADMIN_USER_PANCAKEPHOTO_FORM_NAME, new AdminUserPancakePhotoFormHandler());
	}
	
	/**
	 * 
	 */
	public void configureLanguageModels() {
		LanguageConfigs.put(Language.ENGLISH, new EnglishModelConfig());
		LanguageConfigs.put(Language.GERMAN, new GermanModelConfig());
	}
		
}

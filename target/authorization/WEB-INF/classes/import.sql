--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

-- ------------- Version 4.3 ---------------

-- Actor Roles (** deprecated **) --
INSERT INTO actor(unique_id, name, description, created) VALUES('student_guid', 'student', 'Student', Now());
INSERT INTO actor(unique_id, name, description, created) VALUES('instructor_guid', 'instructor', 'Instructor', Now());
INSERT INTO actor(unique_id, name, description, created) VALUES('author_guid', 'author', 'Author', Now());
INSERT INTO actor(unique_id, name, description, created) VALUES('researcher_guid', 'researcher', 'Researcher', Now());
INSERT INTO actor(unique_id, name, description, created) VALUES('administrator_guid', 'administrator', 'Administrator', Now());

-- Logging Data --
INSERT INTO action(unique_id, name, description, created) VALUES('delete_guid', 'delete', 'Delete', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('edit_guid', 'edit', 'Edit', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('delete_edit_edge', 'delete_guid', 'edit_guid', NOW());
INSERT INTO action(unique_id, name, description, created) VALUES('view_guid', 'view', 'View', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('edit_view_edge', 'edit_guid', 'view_guid', NOW());
INSERT INTO action(unique_id, name, description, created) VALUES('create_guid', 'create', 'Create', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_raw_guid', 'view_raw', 'View raw', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_anon_guid', 'view_anon', 'View anonymized', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('raw_anon_edge', 'view_raw_guid', 'view_anon_guid', Now());

-- Types of Logging Data --
INSERT INTO item(unique_id, name, description, created) VALUES('log_data_guid', 'log_data', 'OLI logging data', Now());
INSERT INTO item(unique_id, name, description, created) VALUES('content_guid', 'content', 'Content', Now());

-- Administrator --
INSERT INTO actor_edge(unique_id, parent_id, child_id, created) VALUES('admin_student_edge', 'administrator_guid', 'student_guid', NOW());
INSERT INTO actor_edge(unique_id, parent_id, child_id, created) VALUES('admin_instructor_edge', 'administrator_guid', 'instructor_guid', NOW());
INSERT INTO actor_edge(unique_id, parent_id, child_id, created) VALUES('admin_author_edge', 'administrator_guid', 'author_guid', NOW());

-- Authorizations --
INSERT INTO item(unique_id, name, description, created) VALUES('authorizations_guid', 'authorizations', 'System Authorizations', NOW());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_delete_auth', 'administrator_guid', 'delete_guid', 'authorizations_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_create_auth', 'administrator_guid', 'create_guid', 'authorizations_guid', Now(), NULL);



-- ------------- Version 4.8 ---------------

-- Courses --
INSERT INTO item(unique_id, name, description, created) VALUES ('courses_guid', 'courses', 'All Courses', Now());
INSERT INTO action(unique_id, name, description, created) VALUES ('manage_section_guid', 'manage_section', 'Manage Section', Now());

-- Announcements --
INSERT INTO item(unique_id, name, description, created) VALUES('announcements_guid', 'announcements', 'Announcements', Now());
INSERT INTO item(unique_id, name, description, created) VALUES('system_announcements_guid', 'system_announcements', 'System Announcements', Now());
INSERT INTO item_edge(unique_id, parent_id, child_id, created) VALUES ('system_announce_edge', 'announcements_guid', 'system_announcements_guid', Now());

INSERT INTO action(unique_id, name, description, created) VALUES('remove_announcement_guid', 'remove_announcement', 'Remove Announcement', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('update_announcement_guid', 'update_announcement', 'Update Announcement', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('remove_update_announcement_guid', 'remove_announcement_guid', 'update_announcement_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('create_announcement_guid', 'create_announcement', 'Create Announcement', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('update_create_announcement_guid', 'update_announcement_guid', 'create_announcement_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_announcement_guid', 'view_announcement', 'View Announcement', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('create_view_announcement_guid', 'create_announcement_guid', 'view_announcement_guid', Now());

-- Assistants --
INSERT INTO action(unique_id, name, description, created) VALUES('remove_assistant_guid', 'remove_assistant', 'Remove Assistant', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('update_assistant_guid', 'update_assistant', 'Update Assistant', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('remove_update_assistant_guid', 'remove_assistant_guid', 'update_assistant_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('create_assistant_guid', 'create_assistant', 'Create Assistant', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('update_create_assistant_guid', 'update_assistant_guid', 'create_assistant_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_assistant_guid', 'view_assistant', 'View Assistant', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('create_view_assistant_guid', 'create_assistant_guid', 'view_assistant_guid', Now());

-- Registrations --
INSERT INTO action(unique_id, name, description, created) VALUES('remove_registration_guid', 'remove_registration', 'Remove Registration', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('update_registration_guid', 'update_registration', 'Update Registration', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('remove_update_registration_guid', 'remove_registration_guid', 'update_registration_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('create_registration_guid', 'create_registration', 'Create Registration', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('update_create_registration_guid', 'update_registration_guid', 'create_registration_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_registration_guid', 'view_registration', 'View Registration', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('create_view_registration_guid', 'create_registration_guid', 'view_registration_guid', Now());

-- Gradebook --
INSERT INTO action(unique_id, name, description, created) VALUES('grade_guid', 'grade', 'Grade Activity', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_gradebook_guid', 'view_gradebook', 'View Gradebook', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('grade_view_edge', 'grade_guid', 'view_gradebook_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_responses_guid', 'view_responses', 'View Responses', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_responses_edge', 'view_gradebook_guid', 'view_responses_guid', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_my_scores_guid', 'view_my_scores', 'View My Scores', Now());

-- Roster ---
INSERT INTO action(unique_id, name, description, created) VALUES('manage_roster_guid', 'manage_roster', 'Manage Roster', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('view_roster_guid', 'view_roster', 'View Roster', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('manage_view_roster_edge', 'manage_roster_guid', 'view_roster_guid', Now());

-- Syllabus --
INSERT INTO action(unique_id, name, description, created) VALUES('view_material_guid', 'view_material', 'View Material', Now());

-- Reports --
INSERT INTO action(unique_id, name, description, created) VALUES('view_report_guid', 'view_report', 'View Section Report', Now());

-- Assist --
INSERT INTO action(unique_id, name, description, created) VALUES('assist_guid', 'assist', 'Assist a Course', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_announcement_assist_edge', 'assist_guid', 'view_announcement_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_gradebook_assist_edge', 'assist_guid', 'view_gradebook_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_roster_assist_edge', 'assist_guid', 'view_roster_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('manage_assist_edge', 'assist_guid', 'manage_section_guid', Now());

-- Instruct --
INSERT INTO action(unique_id, name, description, created) VALUES('instruct_guid', 'instruct', 'Instruct a Course', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('instruct_assist_edge', 'instruct_guid', 'assist_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('manage_roster_instruct_edge', 'instruct_guid', 'manage_roster_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('grade_instruct_edge', 'instruct_guid', 'grade_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('remove_announce_instruct_edge', 'instruct_guid', 'remove_announcement_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('remove_assist_instruct_edge', 'instruct_guid', 'remove_assistant_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_report_instruct_edge', 'assist_guid', 'view_report_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_my_scores_assist_edge', 'assist_guid', 'view_my_scores_guid', Now());

-- Visit --
INSERT INTO action(unique_id, name, description, created) VALUES('visit_guid', 'visit', 'Visit a Course', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_material_visit_edge', 'visit_guid', 'view_material_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_announcement_visit_edge', 'visit_guid', 'view_announcement_guid', Now());

-- Take --
INSERT INTO action(unique_id, name, description, created) VALUES('take_guid', 'take', 'Take a Course', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('take_visit_edge', 'take_guid', 'visit_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_my_scores_take_edge', 'take_guid', 'view_my_scores_guid', Now());

-- Administrator --
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_instruct_guid', 'administrator_guid', 'instruct_guid', 'courses_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_registration_guid', 'administrator_guid', 'remove_registration_guid', 'courses_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_announcement_guid', 'administrator_guid', 'remove_announcement_guid', 'announcements_guid', Now(), NULL);



-- ------------- Version 4.9.5 ---------------

-- Users --
INSERT INTO item(unique_id, name, description, created) VALUES('users_guid', 'users', 'Users', Now());
INSERT INTO action(unique_id, name, description, created) VALUES('manage_user_guid', 'manage_user', 'Manage User', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_manage_user_guid', 'administrator_guid', 'manage_user_guid', 'users_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_delete_user_guid', 'administrator_guid', 'delete_guid', 'users_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_remove_reg_users_guid', 'administrator_guid', 'remove_registration_guid', 'users_guid', Now(), NULL);
INSERT INTO action(unique_id, name, description, created) VALUES('suspend_user_guid', 'suspend_user', 'Suspend User', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_suspend_users_guid', 'administrator_guid', 'suspend_user_guid', 'users_guid', Now(), NULL);

-- Courses --
INSERT INTO action(unique_id, name, description, created) VALUES ('search_guid', 'search', 'Search', NOW());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_search_courses_guid', 'administrator_guid', 'search_guid', 'courses_guid', Now(), NULL);
INSERT INTO action(unique_id, name, description, created) VALUES ('price_section_guid', 'price_section', 'Price Section', NOW());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('admin_price_section_courses_guid', 'administrator_guid', 'price_section_guid', 'courses_guid', Now(), NULL);



-- ------------- Version 5.0 -----------------

-- Content --
INSERT INTO item(unique_id, name, description, created) VALUES ('packages_guid', 'packages', 'Content Packages', Now());
INSERT INTO item(unique_id, name, description, created) VALUES ('organizations_guid', 'organizations', 'Content Organizations', Now());
INSERT INTO item_edge(unique_id, parent_id, child_id, created) VALUES ('content_packages_guid', 'content_guid', 'packages_guid', Now());
INSERT INTO item_edge(unique_id, parent_id, child_id, created) VALUES ('content_organizations_guid', 'content_guid', 'organizations_guid', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_delete_package_guid', 'administrator_guid', 'delete_guid', 'content_guid', Now(), NULL);

-- Courses --
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_delete_courses_guid', 'administrator_guid', 'delete_guid', 'courses_guid', Now(), NULL);
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('delete_instruct_edge', 'instruct_guid', 'delete_guid', Now());

-- Instruct Material --
INSERT INTO action(unique_id, name, description, created) VALUES ('instruct_material_guid', 'instruct_material', 'Instruct Material', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('view_instruct_material_edge', 'instruct_material_guid', 'view_material_guid', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('instruct_material_assist_edge', 'assist_guid', 'instruct_material_guid', Now());



-- ------------- Version 5.2 -----------------

-- Course Builder/Editor --
INSERT INTO action(unique_id, name, description, created) VALUES('schedule_guid', 'schedule', 'Schedule', Now());
INSERT INTO action_edge(unique_id, parent_id, child_id, created) VALUES('instruct_schedule_guid', 'instruct_guid', 'schedule_guid', Now());



-- ------------- Version 5.4a ----------------

-- Administer Courses (change advanced options) --
INSERT INTO action(unique_id, name, description, created) VALUES ('administer_guid', 'administer' ,'Administer', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_administer_guid', 'administrator_guid', 'administer_guid', 'courses_guid', Now(), NULL);



-- ------------- Version 5.14 ----------------

-- Basic LTI --
INSERT INTO item(unique_id, name, description, created) VALUES ('basiclti_lms_institutions_guid', 'basiclti_lms_institutions', 'BasicLTI: LMS Institutions', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_delete_basiclti', 'administrator_guid', 'delete_guid', 'basiclti_lms_institutions_guid', Now(), NULL);



-- ------------- Version 5.16 ----------------

-- Publish app --
INSERT INTO action(unique_id, name, description, created) VALUES ('view_source_guid', 'view_source', 'View Source', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_view_source', 'administrator_guid', 'view_source_guid', 'packages_guid', Now(), NULL);
INSERT INTO action(unique_id, name, description, created) VALUES ('debug_content_guid', 'debug_content', 'Debug Content', Now());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_debug_packages', 'administrator_guid', 'debug_content_guid', 'packages_guid', Now(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_debug_courses', 'administrator_guid', 'debug_content_guid', 'courses_guid', Now(), NULL);



-- ------------- Version 5.17 ----------------

-- Developer role --
INSERT INTO actor(unique_id, name, description, created) VALUES('developer_guid', 'developer', 'Developer', Now());
INSERT INTO actor_edge(unique_id, parent_id, child_id, created) VALUES('admin_developer_edge', 'administrator_guid', 'developer_guid', NOW());
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('developer_debug_courses', 'developer_guid', 'debug_content_guid', 'courses_guid', NOW(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('developer_debug_packages', 'developer_guid', 'debug_content_guid', 'packages_guid', NOW(), NULL);
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES('developer_source_packages', 'developer_guid', 'view_source_guid', 'packages_guid', NOW(), NULL);


-- ------------- Version 5.18 ----------------

-- Visit courses --
INSERT INTO authorization(unique_id, actor_id, action_id, item_id, created, expiration) VALUES ('admin_visit_course', 'administrator_guid', 'visit_guid', 'courses_guid', NOW(), null);

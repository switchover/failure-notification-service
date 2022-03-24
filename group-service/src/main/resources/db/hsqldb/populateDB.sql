INSERT INTO users(id, nickname) VALUES (1, 'user1');
INSERT INTO users(id, nickname) VALUES (2, 'user2');
INSERT INTO users(id, nickname) VALUES (3, 'user3');
INSERT INTO users(id, nickname) VALUES (4, 'user4');
INSERT INTO users(id, nickname) VALUES (5, 'user5');
INSERT INTO users(id, nickname) VALUES (6, 'user6');
INSERT INTO users(id, nickname) VALUES (7, 'user7');
INSERT INTO users(id, nickname) VALUES (8, 'user8');
INSERT INTO users(id, nickname) VALUES (9, 'user9');

INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (1, 'slack', 'SLACK-USER1-TOKEN', 'USER1_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (2, 'slack', 'SLACK-USER2-TOKEN', 'USER2_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (3, 'slack', 'SLACK-USER3-TOKEN', 'USER3_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (4, 'slack', 'SLACK-USER4-TOKEN', 'USER4_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (5, 'slack', 'SLACK-USER5-TOKEN', 'USER5_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (6, 'slack', 'SLACK-USER6-TOKEN', 'USER6_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (7, 'slack', 'SLACK-USER7-TOKEN', 'USER7_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (8, 'slack', 'SLACK-USER8-TOKEN', 'USER8_CHANNEL_ID');
INSERT INTO users_messengers(user_id, service_type, token, target) VALUES (9, 'slack', 'SLACK-USER9-TOKEN', 'USER9_CHANNEL_ID');

INSERT INTO groups(id, groupname) VALUES (1, 'test-team1');
INSERT INTO groups(id, groupname) VALUES (2, 'test-team2');
INSERT INTO groups(id, groupname) VALUES (3, 'test-team3');
INSERT INTO groups(id, groupname) VALUES (4, 'test-team4');
INSERT INTO groups(id, groupname) VALUES (5, 'test-team5');

INSERT INTO groups_users(group_id, user_id) VALUES (1, 1);
INSERT INTO groups_users(group_id, user_id) VALUES (1, 6);
INSERT INTO groups_users(group_id, user_id) VALUES (2, 2);
INSERT INTO groups_users(group_id, user_id) VALUES (2, 7);
INSERT INTO groups_users(group_id, user_id) VALUES (3, 3);
INSERT INTO groups_users(group_id, user_id) VALUES (3, 8);
INSERT INTO groups_users(group_id, user_id) VALUES (4, 4);
INSERT INTO groups_users(group_id, user_id) VALUES (4, 9);
INSERT INTO groups_users(group_id, user_id) VALUES (5, 5);

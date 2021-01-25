INSERT INTO RENTABLES.BRAND (id, name) values ('06cfb586-405a-4f62-a6ad-80de7eff827b','Werner');
INSERT INTO RENTABLES.BRAND (id, name) values ('7b2003ea-f9a4-4dd4-ab0d-a4138b292cd2','Stihl');
INSERT INTO RENTABLES.BRAND (id, name) values ('614debfa-1cd3-4f35-949f-cf6ce85ba749','Ridgid');
INSERT INTO RENTABLES.BRAND (id, name) values ('e770808c-df64-48d6-9594-3a0a46bf8b94','DeWalt');

INSERT INTO RENTABLES.TOOLTYPE (id, name, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('1db82ba9-d0c0-4a4f-98c1-8572001966e5', 'Ladder', 1.99, 1, 1, 0);
INSERT INTO RENTABLES.TOOLTYPE (id, name, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('532db83a-a988-48d7-9a2e-ffcdf0616e08', 'Chainsaw', 1.49, 1, 0, 1);
INSERT INTO RENTABLES.TOOLTYPE (id, name, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES ('e5cbd239-bd22-40bf-a1ed-99ab3429be41', 'Jackhammer', 2.99, 1, 0, 0);

INSERT INTO RENTABLES.TOOL (id, type_id, brand_id, tool_code) VALUES ('b220ed29-9099-4ece-a583-86af2b15c336','1db82ba9-d0c0-4a4f-98c1-8572001966e5','06cfb586-405a-4f62-a6ad-80de7eff827b','LADW');
INSERT INTO RENTABLES.TOOL (id, type_id, brand_id, tool_code) VALUES ('8454a90d-27de-45ec-9c92-28a244ef820b','532db83a-a988-48d7-9a2e-ffcdf0616e08','7b2003ea-f9a4-4dd4-ab0d-a4138b292cd2','CHNS');
INSERT INTO RENTABLES.TOOL (id, type_id, brand_id, tool_code) VALUES ('d4107596-b458-4fdb-86ab-e9416ac82eca','e5cbd239-bd22-40bf-a1ed-99ab3429be41','614debfa-1cd3-4f35-949f-cf6ce85ba749','JAKR');
INSERT INTO RENTABLES.TOOL (id, type_id, brand_id, tool_code) VALUES ('8a8d3c35-4c7b-4176-9267-e4932ef0d424','e5cbd239-bd22-40bf-a1ed-99ab3429be41','e770808c-df64-48d6-9594-3a0a46bf8b94','JAKD');
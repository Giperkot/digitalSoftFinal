CREATE (FSS:Gis {title:'Фонд Социяльного стразования',  mnemonic:'PERSON'})
CREATE (PFR:Gis {title:'Пенсионный фонд России',  mnemonic:'PERSON'})
CREATE (FNS:Gis {title:'Федеральная налоговая служба',  mnemonic:'PERSON'})
CREATE (PERSON:Ou {title:'Данные физического лица',  mnemonic:'PERSON'})
CREATE (LEGAL:Ou {title:'Данные юридичкеского лица',  mnemonic:'LEGAL'})
CREATE (ELN:Ou {title:'Электронный личток нетрудоспособности',  mnemonic:'ELN'})
CREATE (INSURER:Ou {title:'Страхователь фонда социального страхования',  mnemonic:'ELN'})
CREATE (BENEFIT:Ou {title:'Пособия',  mnemonic:'LEGAL'})
CREATE (PAYER:Ou {title:'Плательщик пенсионных взносов',  mnemonic:'LEGAL'})

CREATE (SNILS:Attribute {name:'СНИЛС',  mnemonic:'SNILS'})
CREATE (REPS_INN:Attribute {name:'ИНН физического лица',  mnemonic:'REPS_INN'})
CREATE (LEG_INN:Attribute {name:'ИНН юридического лица',  mnemonic:'REPS_INN'})
CREATE (IP_INN:Attribute {name:'ИНН индивидуального предпринимателя',  mnemonic:'REPS_INN'})
CREATE (FSS_REG_NUM:Attribute {name:'Регистрационный номер ФСС',  mnemonic:'REPS_INN'})
CREATE (ELN_NUM:Attribute {name:'Номер ЭЛН',  mnemonic:'REPS_INN'})
CREATE (FIRST_NAME:Attribute {name:'Имя',  mnemonic:'REPS_INN'})
CREATE (LAST_NAME:Attribute {name:'Фамилия',  mnemonic:'REPS_INN'})
CREATE (MIDDLE_NAME:Attribute {name:'Отчество',  mnemonic:'REPS_INN'})

CREATE
  (SNILS)-[:ACTED_IN {arrts:['snils']}]->(ELN),
  (ELN_NUM)-[:ACTED_IN {arrts:['ln_code']}]->(ELN),
  (FSS_REG_NUM)-[:ACTED_IN {arrts:['insurer']}]->(ELN)

CREATE
  (SNILS)-[:ACTED_IN {arrts:['snils']}]->(PERSON),
  (FIRST_NAME)-[:ACTED_IN {arrts:['ln_code']}]->(PERSON),
  (LAST_NAME)-[:ACTED_IN {arrts:['insurer']}]->(PERSON),
  (MIDDLE_NAME)-[:ACTED_IN {arrts:['insurer']}]->(PERSON),
  (REPS_INN)-[:ACTED_IN {arrts:['insurer']}]->(PERSON)

CREATE
  (ELN)-[:ACTED_IN {arrts:['eln']}]->(FSS),
  (BENEFIT)-[:ACTED_IN {arrts:['benefit']}]->(FSS),
  (INSURER)-[:ACTED_IN {arrts:['insurer']}]->(FSS)

CREATE
  (FSS_REG_NUM)-[:ACTED_IN {arrts:'regnum'}]->(INSURER),
  (LEG_INN)-[:ACTED_IN {arrts:'inn'}]->(INSURER)

CREATE
  (LEG_INN)-[:ACTED_IN {arrts:'inn'}]->(PAYER)

CREATE
  (PAYER)-[:ACTED_IN {arrts:'payer'}]->(PFR)

CREATE
  (LEG_INN)-[:ACTED_IN {arrts:'inn'}]->(LEGAL),
  (FSS_REG_NUM)-[:ACTED_IN {arrts:'inn'}]->(LEGAL)

CREATE
  (LEGAL)-[:ACTED_IN {arrts:'legal'}]->(FNS)


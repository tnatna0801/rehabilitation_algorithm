SELECT animal_id, name
from animal_ins
where intake_condition = 'sick' -- 문자열 비교
order by animal_id;
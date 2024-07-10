
-- 방법1. 서브쿼리, Not in 사용
select c.car_id,
c.car_type,
round(c.daily_fee*30*(100-p.discount_rate)/100) as fee -- 지불 비용

from CAR_RENTAL_COMPANY_CAR c
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
    join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type

-- 2022-11-01 ~ 2022-12-01에 대여가 가능해야함
-- 겹치는 기간을 제외하기 위해서 not in 사용
where c.car_id not in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date <= '2022-12-01' AND end_date >= '2022-11-01'
) and p.duration_type like '30%'
and c.car_type in ('세단', 'SUV') -- 자동차 종류가 세단 내지 SUV

group by c.car_id -- 중복을 제거 / 중복은 history를 보면 car_id가 중복이 될 수 있다.
having fee < 2000000 and fee >= 500000
order by fee desc, c.car_type asc, c.car_id desc;


-- 방법2. 머리 깨질것 같은 방법
-- left join을 사용하면 서브쿼리를 사용안해도 된다. left join에 조건을 추가하면 된다.
SELECT DISTINCT -- 중복제거를 하고 그걸로 이제 having 절 수행
    c.car_id,
    c.car_type,
    ROUND(c.daily_fee * 30 * (100 - p.discount_rate) / 100) AS fee
FROM
    CAR_RENTAL_COMPANY_CAR c
    JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p ON c.car_type = p.car_type
    LEFT JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h ON c.car_id = h.car_id
        AND (h.start_date <= '2022-11-30' AND h.end_date >= '2022-11-01') -- 2022년 11월 1일부터 2022년 11월 30일까지 겹치는 기록
WHERE
    h.car_id IS NULL -- 조인된 대여 기록이 없는 경우 == 주어진 날짜 범위에 대여되지 않은 경우임
    AND p.duration_type = '30일 이상'
    AND c.car_type IN ('세단', 'SUV')
HAVING
    fee < 2000000 AND fee >= 500000
ORDER BY
    fee DESC,
    c.car_type ASC,
    c.car_id DESC;
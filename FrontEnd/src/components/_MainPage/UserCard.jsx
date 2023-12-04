import styles from './Card.module.css';
import React, { useState ,useEffect} from 'react';

const UserCard = ({ userId, name, intro, devTestScore, rank, language, interest, personal, study}) => {

    let token = localStorage.getItem('token') || '';
    // Option Select Input
    const [selectList, setSelectList] = useState([]);
    const [selected, setSelected] = useState("");
    const handleSelect = (e) => {
        setSelected(e.target.value);
    };

    const Suggestion = (e) => {
        e.preventDefault();
        if(!selected) {alert(`프로젝트를 선택해주세요`);}
        else{
        const fetchData = async() => {
            try {
                const res = await fetch(`/suggestRecruitPostEnd`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        userId:userId,
                        recruitPostId:selected,
                    }),
                })
                if (res.ok) {
                    alert("제안 성공");
                } 
                else if (res.status === 400) {
                    alert(`제안에 실패하였습니다.`);
                } else {
                    console.error("실패하였습니다.", res.statusText);
                }
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
         }
    };

    useEffect(() => {
        const fetchData1 = async() => {
            try {
                fetch('/suggestRecruitPost', {
                    method: 'POST', 
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    setSelectList(res);
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData1();  
    }, []);

    
    return (
        <> 
            <div className={styles.UserCard}>                
                <div className={styles.UserName}>{rank} {name}</div>
                <div className={styles.UserIntro}>{intro}</div>
                <div className={styles.UserCate}> 기술스택 </div>
                <div className={styles.UserCateInfo}> 
                {language}
                {language ? language.map((per, index) => (
                    <span key={index}>{index === language.length - 1 ? per : `${per}, `}</span>))
                    : null}
                 </div>
                <div className={styles.UserCate}> 관심분야 </div>
                <div className={styles.UserCateInfo}> 
                {interest ? interest.map((per, index) => (
                    <span key={index}>{index === interest.length - 1 ? per : `${per}, `}</span>))
                    : null} 
                </div>
                <div className={styles.UserCate}> 개인성향 </div>
                <div className={styles.UserCateInfo}> 
                {personal ? personal.map((per, index) => (
                    <span key={index}>{index === personal.length - 1 ? per : `${per}, `}</span>))
                    : null}
                </div>

                {devTestScore}
                <div>{study}</div>
                <select className={styles.pjSelect} onChange={handleSelect} value={selected}>
                    <option value="0">프로젝트 이름 🍊</option>
                    {selectList ? selectList.map((item) => (
                        <option value={item.recruitPostId} key={item.recruitPostId}>
                        {item.title}
                        </option>
                    )): null}
                </select>
                <button onClick={Suggestion} type="submit" className={styles.submitBtn}>제안하기</button>
            </div>
        </>
    );
};

export default UserCard;
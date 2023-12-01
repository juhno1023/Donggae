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
                <div>{name}</div>
                <div>{intro}{devTestScore}{rank}</div>
                <div>#{language}#{interest}#{personal},#{study}</div>
                <select className={styles.pjSelect} onChange={handleSelect} value={selected}>
                    <option value="0">내 프로젝트 🍊</option>
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
import styles from "./Test.module.css"
import React, { useEffect, useState} from 'react';

import { useParams } from 'react-router-dom';


export default function Test() {

    
    let { testId } = useParams();
    let token = localStorage.getItem('token') || '';


    const [problemInfo, setProblemInfo] = useState([]);


    const [items, setItems] = useState([]);
    const addItem = () => {
        // 이전 배열을 복사하고, 새로운 값을 추가한 새 배열을 생성
        const newItems = [...items, 'New Item'];
    
        // 상태 업데이트
        setItems(newItems);
      };
      

    useEffect(() => {
        const fetchData = async () => {
            try {
                fetch(`/test/${testId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                }).then(res=>res.json())        
                .then(res=> {
                    console.log(res);
                    setProblemInfo(res);
                });
            } catch (error) {
                console.error("fatch to fail : ", error);
            }
        };
        fetchData(); 
    }, []);

    const [selectedQuestionIndex, setSelectedQuestionIndex] = useState();
    const handleQuestionSelect = (index) => {
        setSelectedQuestionIndex(index);
      };
    
    return (
        <>
        <div className={styles.label}>
            <div className={styles.title_text}>문제</div>
        </div>

        <div className={styles.question}>
            {problemInfo.map(item => (
                        <div className={styles.tt}>
                        {item.questionNum}.
                        {item.questionText.split('\n').map(line => {
                            return (
                            <>
                                {line}
                                <br />
                            </>
                            );
                        })}
                        {item.answerOptions.map((num,index) => (
                            <>
                            <div>
                                <p>{num.answerNum}. {num.answerText}</p>
                                <button key={index} onClick={() => handleQuestionSelect(index)}></button>
                            </div>
                            </>
                        ))}
                        </div>
                    ))}
            <div>
            
            </div>
        </div>
        </>
    );

}
import styles from "./Test.module.css"
import React, { useEffect, useState} from 'react';
import {Link} from 'react-router-dom'

import { useParams } from 'react-router-dom';


export default function Test() {

    
    let { testId } = useParams();
    let token = localStorage.getItem('token') || '';


    const [problemInfo, setProblemInfo] = useState([]);

    const [result , setResult] = useState([]);

    const [qId, setQId] = useState([]);

    useEffect(() => {
        const fetchData = () => {
            try {
                const res = fetch(`/test/${testId}`, {
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

    const [items, setItems] = useState([]);
    const addItem = (Q, A) => {
        // 이전 배열을 복사하고, 새로운 값을 추가한 새 배열을 생성
        const Data = {
            testQuestionId : Q,
            answerOptionId : A,
        }
    
        const newItems = [...items, Data];
    
        // 상태 업데이트
        setItems(newItems);
      };

    

    const [selectedQuestionIndex, setSelectedQuestionIndex] = useState([]);
    const handleQuestionSelect = (index,event) => {

        const newIndex = [...selectedQuestionIndex];

        newIndex[index] = parseInt(event.target.value,10)

        setSelectedQuestionIndex(newIndex);

        console.log(selectedQuestionIndex);
      };

    const ResultBtn = () => {
        const questionIds = problemInfo.map(item => item.questionId);
        const combinedArray = questionIds.map((value, index) => ({testQuestionId: value, answerOptionId: selectedQuestionIndex[index] }));
        console.log(combinedArray)

        try {
            const res = fetch('/test/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(combinedArray),
            })
            
    
        } catch (error) {
            console.error("Failed to fetch: ", error);
        }
    };
        
    const GradeBtn = () => {
        const res = fetch(`/test/result/${testId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        }).then(res=>res.json())        
        .then(res=> {
            console.log(res);
            setResult(res);
        });
    }

    return (
        <>
        <div className={styles.label}>
            <span className={styles.exit}><Link to='/evaluation'>나가기</Link></span>
            <div className={styles.title_text}>문제
            <button type="submit" onClick={ResultBtn}>문제 저장</button>
            <button type="submit" onClick={GradeBtn}>문제 채점</button>
            <p>{result.correct} / {result.total}</p>
            </div>
        </div>

        <div className={styles.question}>
            {problemInfo.map((item,index) => (
                        <div className={styles.tt}>
                        {item.questionNum}. 
                        {item.questionText.split('\n').map(line => {
                            return (
                            <>
                                {line}
                                <br />
                            
                                <textarea
                                    id={index}
                                    name="content"
                                    placeholder=" "
                                    value={selectedQuestionIndex[index]}
                                    onChange={(e)=>handleQuestionSelect(index,e)}
                                />

                            </>
                            );
                        })}

                        {item.answerOptions.map(num => (
                            <>
                            <div>
                                <p>{num.answerNum}. {num.answerText}</p>
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
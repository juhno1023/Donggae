import styles from "./Evaluation.module.css"
import React, { useEffect, useState} from 'react';

import { useParams } from 'react-router-dom';



export default function Test() {

    
    let { testId } = useParams();
    let token = localStorage.getItem('token') || '';

    console.log(testId);

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
                    console.log(res)
                });
            } catch (error) {
                console.error("fatch to fail : ", error);
            }
        };
        fetchData(); 
    }, []);

    return (
        <div>헐랭</div>
    );
}
import React, { useState ,useEffect } from 'react';
import styles from "./Post.module.css"
import Header from "../../components/_Layout/Header";
import bgImg from '../../image/donggae.png';
import CheckBox from '../../components/CheckBox';
import Modify from './Modify';
import Sidebar from "../../components/_Layout/Sidebars";
import { Link } from 'react-router-dom';
import { useParams } from 'react-router-dom';

export default function Post() {

    let token = localStorage.getItem('token') || '';
    let { recuritPostId } = useParams();

    // 글 수정
    const [isEditing, setIsEditing] = useState(false);
    const checkEdit = localStorage.getItem("checkEdit")

    const [recruitPost, setRecruitPost] = useState('');
    const [recuritField, setRecruitField] = useState([]);
    const [recuritLan, setRecruitLan] = useState('');
    const [recuritPers, setRecruitPers] = useState('');
    const [checkedItems, setCheckedItems] = useState([])

    const datas = [
        { title: '아침식사'},
        { title: '아침간식'},
    ]
    const datas2 = [
        { title: 'd'},
        { title: 'f'},
    ]
    const datas3 = [
        { title: 'dd'},
        { title: 'ff'},
    ]
    const checkedItemHandler = (code, isChecked) => {
        if (isChecked) { //체크 추가할때
            setCheckedItems([...checkedItems, code])
        } else if (!isChecked && checkedItems.find(one => one === code)) {//체크 해제할때 checkedItems에 있을 경우
            const filter = checkedItems.filter(one => one !== code)
            setCheckedItems([...filter])
        }
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form Data:', checkedItems);
    };
    const Deletion = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`/recruitPost/${recuritPostId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
            })
            if (response.ok) {
                alert("삭제 완료");
                window.location.replace("/userteam");
            } else if (response.status === 400) {
                const errorText = await response.text();
                alert(`일치하지 않습니다. ${errorText}`);
                console.error("삭제 실패 : ", errorText);
            } else {
                console.error("삭제 실패 : ", response.statusText);
            }
            
        } catch (error) {
            console.error("fatch to fail : ", error);
        }
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                fetch(`/recruitPost/${recuritPostId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                }).then(res=>res.json())        
                .then(res=> {
                    setRecruitPost(res)
                    setRecruitField(res.recruitFields)
                    setRecruitLan(res.recruitLanguages)
                    setRecruitPers(res.recruitPersonalities)
                });
            } catch (error) {
                console.error("fatch to fail : ", error);
            }
        };
        fetchData(); 
    }, []);

    if(isEditing){
        return(
            <Modify post={recruitPost}></Modify>
        )
    }else{
    return(
        <div className={styles.default}>
        <Header /><Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
            {checkEdit == "true" ? <button onClick={()=>setIsEditing(true)} type="submit" className={styles.modifyBtn}>수정하기</button> : null}
            {checkEdit == "true" ? <button onClick={Deletion} type="submit" className={styles.deleteBtn}>삭제하기</button> : null}
                <Link to='/application'><button type="submit" className={styles.submitBtn}>지원하기</button></Link>
                <div  className={styles.text__1}>🧡{recruitPost.title}🧡</div>
                <div className={styles.formGroup}>
                    <div>{recruitPost.content}</div>
                </div>
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                <div className={styles.text__1}>팀장 정보</div>
                <div className={styles.profile_box}>
                    <div className={styles.logo}>
                    <img className={styles.logoimg} src={bgImg} alt="Donggae Logo" />
                    <div className={styles.profile_info}>
                        <div className={styles.text__2}>{recruitPost.githubName}</div>
                        <br></br>
                        {recruitPost.selfIntro}
                    </div>
                    </div>
                </div>
                <div className={styles.profile_more}>
                    <div className={styles.keyword_box}>
                        <div className={styles.keyword}>
                            모집 분야
                            {recuritField ? recuritField.slice(0, 2).map((item, index) => (<span>{item.field}</span>)) : null}
                        </div>
                        <div className={styles.keyword}>
                            선호 언어
                            {recuritLan ? recuritLan.slice(0, 2).map((item, index) => (<span key={index}>{item.language}</span>)) : null}

                        </div>
                    </div>
                    <div className={styles.keyword_box}>
                        <div className={styles.keyword}>
                            성격 특성
                            {recuritPers ? recuritPers.slice(0, 2).map((item, index) => (<span key={index}>{item.personality}</span>)) : null}
                        </div>
                    </div>
                </div>
                
                </div>
                <div className={styles.half}>
                    <div className={styles.text__1}>세부사항 설정</div> 모집 분야
                    <div className={styles.container}>
                        <div>
                        {datas.map(data => <CheckBox data={data.title} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
                        </div>
                    </div>
                    선호 언어
                    <div className={styles.container}>
                        <div>
                        {datas2.map(data => <CheckBox data={data.title} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
                        </div>
                    </div>
                    선호 성향
                    <div className={styles.container}>
                        <div>
                        {datas3.map(data => <CheckBox data={data.title} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        </div>    
    )}
}
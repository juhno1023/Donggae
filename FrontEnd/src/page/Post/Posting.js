import React, { useState  } from 'react';
import styles from "./Posting.module.css"
import { useNavigate } from "react-router-dom";
import Sidebar from "../../components/_Layout/Sidebars";
import Header from "../../components/_Layout/Header";
import CheckBox from '../../components/CheckBox';
import MultiSelect from '../../components/_Tool/Multiselect';

export default function Posting() {
    let token = localStorage.getItem('token') || '';
    const [recruitFields, setrecruitFields] = useState([])
    const [recruitLanguages, setrecruitLanguages] = useState([])
    const [recruitPersonalities, setrecruitPersonalities] = useState([])
    const [formData, setFormData] = useState({
        title: '',
        content: '',
        majorLectureName: null,
        recruitFields: [],
        recruitLanguages: [],
        recruitPersonalities: [],
        teamName: '',
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    // checkbox Input
    const datas = [
        { name: "BackEnd", id: 1 },
        { name: "FrontEn", id: 2 },
        { name: "iOS", id: 3 },
        { name: "AI", id: 4 },
        { name: "Option 5", id: 5 },
        { name: "AUIUXI", id: 6 },
      ];
    const datas2 = [
        { title: 'JavaScript'},
        { title: 'TypeScript'},
        { title: 'Vue'},
        { title: 'Nodejs'},
    ]
    const datas3 = [
        { title: '계획적인'},
        { title: '논리적인'},
        { title: '꼼꼼한'},
    ]

    const checkedItemHandler = (itemName, stateKey) => {
        setFormData((prevData) => ({
            ...prevData,
            [stateKey]: prevData[stateKey].includes(itemName)
                ? prevData[stateKey].filter((item) => item !== itemName)
                : [...prevData[stateKey], itemName],
        }));
    };
    // Option Select Input
    const selectList = ["없음", "apple", "banana", "grape", "orange"];
    const [Selected, setSelected] = useState("");

    const handleSelect = (e) => {
        setSelected(e.target.value);
    };


    const PostOn = (e) => {
        e.preventDefault();
        console.log(formData);
        console.log(Selected)
        const fetchData = async() => {
            try {
                const res = await fetch('http://localhost:8080/recruitPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(formData),
                })
                if (res.ok) {
                    alert("작성 완료");
                    window.location.replace("/userteam");
                } 
                else if (res.status === 400) {
                    alert(`작성에 실패하였습니다.`);
                } else {
                    console.error("작성에 실패하였습니다.", res.statusText);
                }
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    };

    {}
    return (
        <div className={styles.default}>
          <Header /><Sidebar/>
          <div className={styles.inner}>
                <div className={styles.body}>
                <form onSubmit={PostOn}>
                    <button type="submit" className={styles.submitBtn}>작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                    <label>팀명</label>
                    <input
                        type="text"
                        id="team_name"
                        name="teamName"
                        placeholder="팀 이름을 작성해주세요."
                        value={formData.teamName}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg2}`}>
                    <label>제목</label>
                    <input
                        type="text"
                        id="post_title"
                        name="title"
                        placeholder="제목을 작성해주세요. (예시 : 함께 ㅇㅇ 프로젝트를 이끌어 갈 분들을 모집합니다! )"
                        value={formData.title}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                    <label>내용</label>
                    <textarea
                        id="post_content"
                        name="content"
                        placeholder="내용을 작성해주세요. (예시 : 이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs, ...)"
                        value={formData.content}
                        onChange={handleInputChange}
                    />
                    </div>
                </form>
                
                <div className={styles.box__}>
                    <div className={styles.half}>
                        <div className={styles.text__1}>세부사항 설정</div>
                        모집 분야
                        <div className={styles.container}>
                            <MultiSelect options ={datas} placehdr = '모집분야'/>
                        </div>
                        선호 언어
                        <div className={styles.container}>
                            <MultiSelect options ={datas} placehdr = '선호언어'/>
                        </div>
                        선호 성향
                        <div className={styles.container}>
                            <MultiSelect options ={datas} placehdr = '선호성향'/>
                        </div>
                    </div>
                    <div className={styles.half}>
                    <div className={styles.text__1}>전공강의 팀원 모집하기</div> 선택 된 수강강의
                    <select className={styles.majorSelect} onChange={handleSelect} value={Selected}>
                        {selectList.map((item) => (
                            <option value={item} key={item}>
                            {item}
                            </option>
                        ))}
                    </select>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}
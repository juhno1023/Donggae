import React, { useState  } from 'react';
import styles from "./Posting.module.css"
import { useNavigate, useParams } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import CheckBox from '../../components/CheckBox';
import Sidebar from "../../components/_Layout/Sidebars";

export default function Modify({post}) {
    let token = localStorage.getItem('token') || '';
    let { recuritPostId } = useParams();
    let checkEdit = "false"
    localStorage.setItem("checkEdit", checkEdit)

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
        { title: 'BackEnd'},
        { title: 'IOS'},
        { title: 'Android'},
    ]
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
        const fetchData = async() => {
            try {
                const res = await fetch(`/recruitPost/${recuritPostId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(formData),
                })
                if (res.ok) {
                    alert("수정 완료");
                    window.location.replace(`/post/${recuritPostId}`);
                } 
                else if (res.status === 400) {
                    alert(`수정에 실패하였습니다.`);
                } else {
                    console.error("실패하였습니다.", res.statusText);
                }
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    };

    return (
        <div className={styles.default}>
          <Header /><Sidebar/>
          <div className={styles.inner}>
                <div className={styles.body}>
                <form onSubmit={PostOn}>
                    <button type="submit" className={styles.submitBtn}>수정완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                    <label>팀명</label>{post.teamName}
                    <input
                        type="text"
                        id="team_name"
                        name="teamName"
                        placeholder={post.teamName}
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
                        placeholder={post.title}
                        value={formData.title}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                    <label>내용</label>
                    <textarea
                        id="post_content"
                        name="content"
                        placeholder={post.content}
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
                            <div>
                            {datas.map(data => 
                                <CheckBox 
                                    data={data.title} 
                                    checkedItems={recruitFields} 
                                    checkedItemHandler={(itemName) => checkedItemHandler(itemName, 'recruitFields')}
                                />
                            )}
                            </div>
                        </div>
                        선호 언어
                        <div className={styles.container}>
                            <div>
                            {datas2.map(data => 
                                <CheckBox
                                    data={data.title}
                                    checkedItems={formData.recruitLanguages}
                                    checkedItemHandler={(itemName) => checkedItemHandler(itemName, 'recruitLanguages')}
                                />
                            )}
                            </div>
                        </div>
                        선호 성향
                        <div className={styles.container}>
                            <div>
                            {datas3.map(data => 
                                <CheckBox
                                    data={data.title}
                                    checkedItems={formData.recruitPersonalities}
                                    checkedItemHandler={(itemName) => checkedItemHandler(itemName, 'recruitPersonalities')}
                                />
                            )}
                            </div>
                        </div>
                    </div>
                    <div className={styles.half}>
                    <div className={styles.text__1}>전공강의 팀원 모집하기</div> 선택 된 수강강의
                    <select onChange={handleSelect} value={Selected}>
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
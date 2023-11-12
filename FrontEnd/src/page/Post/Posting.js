import React, { useState  } from 'react';
import styles from "./Posting.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import CheckBox from '../../components/CheckBox';

export default function Posting() {
    // 팀명, 제목, 내용 등 text Input
    const [formData, setFormData] = useState({
        team_name: '',
        post_title: '',
        post_content: '',
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    // checkbox Input
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

    // Option Select Input
    const selectList = ["없음", "apple", "banana", "grape", "orange"];
    const [Selected, setSelected] = useState("");

    const handleSelect = (e) => {
        setSelected(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form Data:', formData);
        console.log('checkedItems:', checkedItems);
        console.log('selectList:', Selected);
    };

    return (
        <div className={styles.default}>
          <Header />
          <div className={styles.inner}>
                <div className={styles.body}>
                <form onSubmit={handleSubmit}>
                    <button type="submit">작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                    <label htmlFor="team_name">팀명</label>
                    <input
                        type="text"
                        id="team_name"
                        name="team_name"
                        placeholder="팀 이름을 작성해주세요."
                        value={formData.team_name}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg2}`}>
                    <label htmlFor="post_title">제목</label>
                    <input
                        type="text"
                        id="post_title"
                        name="post_title"
                        placeholder="제목을 작성해주세요. (예시 : 함께 ㅇㅇ 프로젝트를 이끌어 갈 분들을 모집합니다! )"
                        value={formData.post_title}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                    <label htmlFor="post_content">내용</label>
                    <textarea
                        id="post_content"
                        name="post_content"
                        placeholder="내용을 작성해주세요. (예시 : 이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs, ...)"
                        value={formData.post_content}
                        onChange={handleInputChange}
                    />
                    </div>
                </form>
                
                <div className={styles.box__}>
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
import styles from "./Header.module.css";
import { useNavigate, Link } from "react-router-dom";
import { useState } from "react";

export default function Header() {
  return (
    <div className={styles.header}>
      <div className={styles.contents}>
        <div className={styles.logo}>
          <div className={styles.title}>Donggae</div>
          <div className={styles.subtitle}>
            <b>동</b>국대 <b>개</b>발자들
          </div>
        </div>
        <div className={styles.search}>
          <input type="text" placeholder="검색어 입력" />
          <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
        </div>
      </div>
    </div>
  );
}
